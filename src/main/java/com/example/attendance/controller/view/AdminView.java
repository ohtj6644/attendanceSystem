package com.example.attendance.controller.view;

import com.example.attendance.entity.SiteUser;
import com.example.attendance.service.UserService;
import com.example.attendance.user.UserCreateForm;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class AdminView {


    private final UserService userService;
    //----------------유저 회원가입 화면 반환 ------------------//
    @GetMapping("/admin/signup")
    @PreAuthorize("isAuthenticated()")
    public String signUp( Model model, Principal principal){

        SiteUser user = userService.findUser(principal.getName());

        model.addAttribute("userCreateForm", new UserCreateForm());

        model.addAttribute("user",user);

        return "/admin/admin_main";

    }

    //----------------유저 구성원목록 화면 반환 ------------------//
    @GetMapping("/admin/userlist")
    @PreAuthorize("isAuthenticated()")
    public String userList(Model model, Principal principal, @RequestParam(value = "page",defaultValue = "0")int page){

        SiteUser user = userService.findUser(principal.getName());
        Page<SiteUser> userList = this.userService.getList(page);

        model.addAttribute("user",user);
        model.addAttribute("paging",userList);

        return "/admin/admin_user_list";

    }



}
