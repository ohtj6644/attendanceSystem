package com.example.attendance.service;

import com.example.attendance.entity.Annual;
import com.example.attendance.entity.SiteUser;
import com.example.attendance.repo.AnnualRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AnnualService {


    private final AnnualRepository AnnualRepo;

    public void annualEnroll(SiteUser user, LocalDate annualDate,String reason){
        Annual annual = new Annual();
        annual.setUser(user);
        annual.setAnnualDate(annualDate);
        annual.setReason(reason);
        this.AnnualRepo.save(annual);

    }
}
