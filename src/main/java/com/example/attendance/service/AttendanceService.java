package com.example.attendance.service;


import com.example.attendance.entity.Annual;
import com.example.attendance.entity.Attendance;
import com.example.attendance.entity.SiteUser;
import com.example.attendance.repo.AttendanceRepository;
import com.example.attendance.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.apache.tomcat.jni.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private  final AttendanceRepository attenRepo;
    private final UserRepository userRepository;


    //-------------------------------근무 시작 메서드------------------------//
    public String startWork(SiteUser user){
        Attendance attendance = new Attendance();
        attendance.setId(LocalDate.now().toString()+user.getUsername());
        attendance.setUser(user);
        attendance.setStartWorkTime(LocalDateTime.now());
        this.attenRepo.save(attendance);

        return attendance.getId();

    }
    //-------------------------------근무 종료 메서드-----------------------//
    public  String endWork(Attendance attendance){
        //근무 종료시간 저장
        attendance.setEndWorkTime(LocalDateTime.now());

        //근무시작 과 끝 의 차 구함.
        Duration duration=Duration.between(attendance.getStartWorkTime(),attendance.getEndWorkTime());

        int hours= duration.toHoursPart()*60;
        int minutes = duration.toMinutesPart();

        //구한 시간차를 근무시간에 저장.
        attendance.setWorkTime(hours+minutes);

        this.attenRepo.save(attendance);

        return attendance.getId();

    }


    //-------------------해당 날짜에 이미 만들어진 근무가 있는지 확인------------//
    public Attendance getNowAttendance(SiteUser user){
        String id = LocalDate.now().toString()+user.getUsername();

        return this.attenRepo.findByIdAndUser(id,user);
    }



    //-------------------한달간 근무내역 확인 ----------------------------------//
    public List<Attendance> getMonthAttendance(SiteUser user, int month , int year){
        LocalDateTime startOfMonth = LocalDateTime.of(year, month, 1, 0, 0);
        LocalDateTime endOfMonth = startOfMonth.plusMonths(1).minusSeconds(1);

        return attenRepo.findByUserAndStartWorkTimeBetween(user, startOfMonth, endOfMonth);
    }

    //--------------------어제 날짜의 근무 전체 반환 ------------------------------------//
    public List<Attendance> getYesterdayAttendance(){
        LocalDateTime start = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.MIN);
        LocalDateTime end =LocalDateTime.of(LocalDate.now().minusDays(1),LocalTime.MAX);
        // 전날 00시 / 24시 가져오기.


        return attenRepo.findByStartWorkTimeBetween(start,end);
    }

    //----------------------근무 삭제 ------------------------------------------------//
    public void deleteAttendance(Attendance attendance){
        this.attenRepo.delete(attendance);

    }


    //----------------------유저 기간별 근무 조회  ------------------------------------------------//
    public Page<Attendance> getSearchAttendance(LocalDate startDate, LocalDate endDate,SiteUser user,int page){
        List<Sort.Order> sorts=new ArrayList<>();
        sorts.add(Sort.Order.desc("startWorkTime"));
        Pageable pageable = PageRequest.of(page,93,Sort.by(sorts));
        return this.attenRepo.findByStartWorkTimeBetweenAndUser(startDate, endDate, user, pageable);
    }

    //-------------------연차사용 근태 생성 --------------------------//
    public void  annualEnrollOk(Annual annual){
        Attendance attendance = new Attendance();
        attendance.setId(LocalDate.now().toString()+annual.getUser().getUsername());
        attendance.setUser(annual.getUser());
        attendance.setStartWorkTime(annual.getAnnualDate().atTime( 9,00));
        attendance.setEndWorkTime(annual.getAnnualDate().atTime(18,00));
        attendance.setAnnual("사용");
        this.attenRepo.save(attendance);
    }


}




