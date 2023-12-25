package com.example.attendance.controller.view;

import com.example.attendance.user.UserCreateForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminView {

    //----------------유저 회원가입 화면 반환 ------------------//
    @GetMapping("/admin")
    public String signUp(UserCreateForm userCreateForm){

        return "/admin/admin_main";

    }
}
