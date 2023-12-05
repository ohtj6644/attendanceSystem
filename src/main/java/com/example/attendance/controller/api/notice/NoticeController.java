package com.example.attendance.controller.api.notice;


import com.example.attendance.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NoticeController {


    private final NoticeService noticeService;


    @PostMapping("/notice/create")
    public ResponseEntity<String> noticeCreate(){

       return ResponseEntity.ok( "등록 완료 ");
    }

}
