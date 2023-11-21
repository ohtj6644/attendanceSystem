package com.example.attendance.controller.view;


import com.example.attendance.user.UserCreateForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class viewController {



    @GetMapping("/signup")
    public String signUp(UserCreateForm userCreateForm){

        return "sign_up";

    }
}
