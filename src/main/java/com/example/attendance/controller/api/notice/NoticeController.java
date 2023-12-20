package com.example.attendance.controller.api.notice;


import com.example.attendance.entity.SiteUser;
import com.example.attendance.service.NoticeService;
import com.example.attendance.service.UserService;
import com.example.attendance.user.UserRole;
import feign.form.FormData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class NoticeController {


    private final NoticeService noticeService;
    private final UserService userService;

    //-------------공지사항 추가----------------//
    @PostMapping("/notice/create")
    public ResponseEntity<String> noticeCreate(@RequestParam("noticeText") String formData, Principal principal){

        SiteUser user= this.userService.findUser(principal.getName());

        if (user.getRole()!= UserRole.ADMIN){
            return ResponseEntity.badRequest().body("권한이 없습니다.");
        }
        this.noticeService.noticeCreate(formData,user);

       return ResponseEntity.ok( "등록 완료 ");
    }


    //-------------공지사항 삭제----------------//
    @DeleteMapping("/notice/delete/{id}")
    public ResponseEntity<String> noticeDelete(@PathVariable("id")String id,Principal principal){
        SiteUser user= this.userService.findUser(principal.getName());
        if (user.getRole()!= UserRole.ADMIN){
            return ResponseEntity.badRequest().body("권한이 없습니다.");
        }else{
            this.noticeService.noticeDelete(id);
            return ResponseEntity.ok( "삭제 완료 ");
        }

    }

    //-------------공지사항 수정----------------//
    @PutMapping("/notice/modif/{id}")
    public ResponseEntity<String> noticeModify(@PathVariable("id")String id, Principal principal ,@RequestParam(value = "content") String newContent ){

        SiteUser user= this.userService.findUser(principal.getName());
        if (user.getRole()!= UserRole.ADMIN){
            return ResponseEntity.badRequest().body("권한이 없습니다.");
        }else {
            this.noticeService.noticeModify(id,newContent);
            return ResponseEntity.ok( "수정 완료 ");
        }
    }


}
