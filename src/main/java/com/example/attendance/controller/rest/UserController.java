package com.example.attendance.controller.rest;


import com.example.attendance.service.UserService;
import com.example.attendance.entity.SiteUser;
import com.example.attendance.user.UserCreateForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
public class UserController {

    private  final UserService userService;


    //---------------------------유저생성---------------------------//
    @PostMapping("/signup")
    public ResponseEntity<String> signupUser(@ModelAttribute("userCreateForm") UserCreateForm userCreateForm, BindingResult bindingResult) {

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

        // 유저 생성
        SiteUser user = this.userService.newUser(userCreateForm.getUsername(), userCreateForm.getPassword1());

        // JSON 응답 반환
        return ResponseEntity.ok("유저 생성 완료. 유저번호:" + user.getUuid());
    }

}
