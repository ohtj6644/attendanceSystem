package com.example.attendance.controller.view;

import com.example.attendance.entity.SiteUser;
import com.example.attendance.service.UserService;
import com.example.attendance.user.UserCreateForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class AdminView {


    private final UserService userService;
    //----------------유저 회원가입 화면 반환 ------------------//
    @GetMapping("/admin")
    @PreAuthorize("isAuthenticated()")
    public String signUp(UserCreateForm userCreateForm, Model model, Principal principal){

        SiteUser user = userService.findUser(principal.getName());

        model.addAttribute("user",user);

        return "/admin/admin_main";

    }
}
