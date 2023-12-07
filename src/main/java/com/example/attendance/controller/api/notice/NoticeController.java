package com.example.attendance.controller.api.notice;


import com.example.attendance.entity.SiteUser;
import com.example.attendance.service.NoticeService;
import com.example.attendance.service.UserService;
import feign.form.FormData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class NoticeController {


    private final NoticeService noticeService;
    private final UserService userService;


    @PostMapping("/admin/notice/create")
    public ResponseEntity<String> noticeCreate(@RequestBody FormData formData, Principal principal){

        SiteUser user= this.userService.findUser(principal.getName());

        this.noticeService.noticeCreate(formData,user);

       return ResponseEntity.ok( "등록 완료 ");
    }

}
