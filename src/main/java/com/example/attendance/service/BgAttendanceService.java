package com.example.attendance.service;


import com.example.attendance.entity.Annual;
import com.example.attendance.entity.BgAttendance;
import com.example.attendance.entity.SiteUser;
import com.example.attendance.repo.BgAttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        bgAttendance.setApproval("신청");
        this.BgRepo.save(bgAttendance);
    }

    public BgAttendance getBgOne(String id){
        Optional<BgAttendance> temp=this.BgRepo.findById(id);
        BgAttendance bg= temp.get();
        return bg;
    }

    public void bgCancel(BgAttendance bg){
        bg.setApproval("취소");
        this.BgRepo.save(bg);
    }

    public Page<BgAttendance> getUserList(SiteUser user,int page){
        List<Sort.Order> sorts=new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page,15,Sort.by(sorts));
        return this.BgRepo.findByUser(pageable, user);

    }

    public Page<BgAttendance> getBgAttendancelList(int page){
        List<Sort.Order> sorts=new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page,15,Sort.by(sorts));
        return this.BgRepo.findAll(pageable);

    }

    //--------------------외근 승인 -----------------------//
    public void bgEnrollOk(BgAttendance bgAttendance , SiteUser user){
        bgAttendance.setApproval("승인");
        bgAttendance.setApprovalUser(user);
        this.BgRepo.save(bgAttendance);
    }


    //--------------------외근 반려 -----------------------//
    public void bgEnrollNo(BgAttendance bgAttendance, SiteUser user){
        bgAttendance.setApprovalUser(user);
        bgAttendance.setApproval("반려");
        this.BgRepo.save(bgAttendance);
    }
}
