package com.example.attendance.service;


import com.example.attendance.entity.BgAttendance;
import com.example.attendance.entity.SiteUser;
import com.example.attendance.repo.BgAttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BgAttendanceService {

    private final BgAttendanceRepository BgRepo;

    public void bgEnroll(SiteUser user, LocalDate bgDate, String area){
        BgAttendance bgAttendance = new BgAttendance();
        bgAttendance.setArea(area);
        bgAttendance.setUser(user);
        bgAttendance.setBgDate(bgDate);
        bgAttendance.setCreateDate(LocalDateTime.now());
        this.BgRepo.save(bgAttendance);
    };
}
