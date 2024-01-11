package com.example.attendance.service;

import com.example.attendance.entity.Annual;
import com.example.attendance.entity.SiteUser;
import com.example.attendance.repo.AnnualRepository;
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
public class AnnualService {


    private final AnnualRepository AnnualRepo;

    public void annualEnroll(SiteUser user, LocalDateTime annualDate, String reason){
        Annual annual = new Annual();
        annual.setUser(user);
        annual.setAnnualDate(annualDate.toLocalDate());
        annual.setReason(reason);
        annual.setCreateDate(LocalDateTime.now());
        annual.setApproval("신청");
        this.AnnualRepo.save(annual);

    }

    public Page<Annual> getUserList(SiteUser user , int page){
        List<Sort.Order> sorts=new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page,15,Sort.by(sorts));
        return this.AnnualRepo.findAllAndUser(pageable, user);
    }


    public Annual getAnnualOne(String id ){
        Optional<Annual> temp=this.AnnualRepo.findById(id);
        Annual annual= temp.get();
        return annual;
    }

    public void  annualCancel(Annual annual){
        annual.setApproval("취소");
        this.AnnualRepo.save(annual);
    }
}
