package com.example.attendance.service;

import com.example.attendance.entity.Notice;
import com.example.attendance.entity.SiteUser;
import com.example.attendance.repo.NoticeRepository;
import feign.form.FormData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;


    public void noticeCreate(FormData formData, SiteUser user){
        Notice notice=new Notice();
        notice.setCreateDate(LocalDateTime.now());
        notice.setContent(formData.getContentType());
        notice.setAuthor(user);

        this.noticeRepository.save(notice);


    }

}
