package com.example.attendance.service;


import com.example.attendance.entity.CompanyFile;
import com.example.attendance.entity.SiteUser;
import com.example.attendance.repo.CompanyFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyFileService {

    private final CompanyFileRepository companyFileRepo;


    public Page<CompanyFile> getCompanyFileList(int page) {

        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return this.companyFileRepo.findAll(pageable);

    }

    public void createCompanyFile(String fileName, String fileUrl) {
        CompanyFile companyFile = new CompanyFile();
        companyFile.setFileName(fileName);
        companyFile.setFileUrl(fileUrl);
        companyFile.setCreateDate(LocalDate.now());
        this.companyFileRepo.save(companyFile);
    }


    public CompanyFile getCompanyFile(String id){
        Optional<CompanyFile> temp= this.companyFileRepo.findById(id);
        CompanyFile companyFile = temp.get();
        return  companyFile;
    }

    public void companyFileDelete(CompanyFile companyFile){

        this.companyFileRepo.deleteById(companyFile.getId());
    }
}
