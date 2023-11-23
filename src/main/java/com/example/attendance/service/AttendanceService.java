package com.example.attendance.service;


import com.example.attendance.entity.Attendance;
import com.example.attendance.entity.SiteUser;
import com.example.attendance.repo.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private  final AttendanceRepository attenRepo;


    // 근무 시작 메서드 //
    public String startWork(SiteUser user){
        Attendance attendance = new Attendance();
        attendance.setId(LocalDate.now().toString());
        attendance.setUser(user);
        attendance.setStartWorkTime(LocalDateTime.now());
        this.attenRepo.insert(attendance);

        return attendance.getId();

    }

    //해당 날짜에 이미 만들어진 근무가 있는지 확인
    public Attendance getNowAttendance(){
        String id = LocalDate.now().toString();

        Optional<Attendance> optional = this.attenRepo.findById(id);

        return optional.get();
    }



}
