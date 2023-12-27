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
    public ResponseEntity<String> signupUser(@ModelAttribute UserCreateForm userCreateForm) {

        try {
            // 패스워드 일치 여부 확인
            if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
                return ResponseEntity.badRequest().body("패스워드 불일치");
            }

            if (this.userService.findUser(userCreateForm.getUsername()) != null) {
                return ResponseEntity.badRequest().body("이미 존재하는 id입니다");
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            LocalDate signupDate = LocalDate.parse(userCreateForm.getSignupDate(), formatter);
            LocalDateTime signupDateTime = signupDate.atStartOfDay();

            // 유저 생성
            SiteUser user = this.userService.newUser(
                    userCreateForm.getUsername(),
                    userCreateForm.getPassword1(),
                    userCreateForm.getRealName(),
                    signupDateTime
            );

            // JSON 응답 반환
            return ResponseEntity.ok("유저 생성 완료. 유저번호:" + user.getUuid());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("회원가입에 실패하였습니다");
        }
    }


    //---------------------로그아웃--------------------//
    @GetMapping("user/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.removeAttribute("loggedIn");

        return ResponseEntity.ok("로그아웃 되었습니다 ");
    }

    //---------------------관리자권한 추가 --------------------//
    @GetMapping("/admin/user/grantup/{id}")
    public ResponseEntity<String> grantUp(@PathVariable("id")String id){

        try{
            this.userService.grantUp(id);
            return ResponseEntity.ok(id+"번 유저에게 관리자 권한이 추가되었습니다.");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("권한부여 실패 ");
        }

    }



}
