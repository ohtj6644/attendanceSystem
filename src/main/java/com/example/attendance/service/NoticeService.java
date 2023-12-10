package com.example.attendance.service;

import com.example.attendance.entity.Notice;
import com.example.attendance.entity.SiteUser;
import com.example.attendance.repo.NoticeRepository;
import feign.form.FormData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;


    public void noticeCreate(String formData, SiteUser user){
        Notice notice=new Notice();
        notice.setCreateDate(LocalDateTime.now());
        notice.setContent(formData);
        notice.setAuthor(user);

        this.noticeRepository.save(notice);


    }

    public List<Notice> getList(){

        return this.noticeRepository.findAll();
    }

}
