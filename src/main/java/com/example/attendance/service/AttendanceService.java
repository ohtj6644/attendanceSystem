package com.example.attendance.service;


import com.example.attendance.entity.Attendance;
import com.example.attendance.entity.SiteUser;
import com.example.attendance.repo.AttendanceRepository;
import com.example.attendance.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.apache.tomcat.jni.Local;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
        this.attenRepo.insert(attendance);

        return attendance.getId();

    }
    //-------------------------------근무 종료 메서드-----------------------//
    public  String endWork(Attendance attendance){
        //근무 종료시간 저장
        attendance.setEndWorkTime(LocalDateTime.now());

        //근무시작 과 끝 의 차 구함.
        Duration duration=Duration.between(attendance.getStartWorkTime(),attendance.getEndWorkTime());
        long milliseconds = duration.toMillis();
        Time time = new Time(milliseconds);

        //구한 시간차를 근무시간에 저장.
        attendance.setWorkTime(time);

        this.attenRepo.save(attendance);

        return attendance.getId();

    }


    //-------------------해당 날짜에 이미 만들어진 근무가 있는지 확인------------//
    public Attendance getNowAttendance(SiteUser user){
        String id = LocalDate.now().toString()+user.getUsername();

        return this.attenRepo.findByIdAndUser(id,user);
    }



    //-------------------한달간 근무내역 확인 ----------------------------------//
    public List<Attendance> getMonthAttendance(SiteUser user, int page){



    }



}
