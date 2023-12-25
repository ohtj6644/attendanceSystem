package com.example.attendance.controller.api.user;


import com.example.attendance.service.UserService;
import com.example.attendance.entity.SiteUser;
import com.example.attendance.user.UserCreateForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


@RestController
@RequiredArgsConstructor
public class UserController {

    private  final UserService userService;


    //---------------------------유저생성---------------------------////
    @PostMapping("/admin/signup")
    public ResponseEntity<String> signupUser(@Validated @ModelAttribute("userCreateForm") UserCreateForm userCreateForm, BindingResult bindingResult) {

        // 유효성 검사 에러 처리
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("유효성 검사 에러 발생");
        }

        // 패스워드 일치 여부 확인
        if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
            return ResponseEntity.badRequest().body("패스워드 불일치");
        }

        if (this.userService.findUser(userCreateForm.getUsername()) != null) {
            return ResponseEntity.badRequest().body("이미 존재하는 id입니다");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate signupDate = LocalDate.parse(userCreateForm.getSignupDate(), formatter);
        LocalDateTime signupDateTime=signupDate.atTime(LocalTime.MIDNIGHT);

        // 유저 생성
        SiteUser user = this.userService.newUser(userCreateForm.getUsername(), userCreateForm.getPassword1(),userCreateForm.getRealName(),signupDateTime);

        // JSON 응답 반환
        return ResponseEntity.ok("유저 생성 완료. 유저번호:" + user.getUuid());
    }


    //---------------------로그아웃--------------------//
    @GetMapping("user/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.removeAttribute("loggedIn");

        return ResponseEntity.ok("로그아웃 되었습니다 ");
    }



}
