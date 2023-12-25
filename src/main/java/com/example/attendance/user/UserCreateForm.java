package com.example.attendance.user;


import lombok.Getter;
import lombok.Setter;

import org.springframework.boot.convert.DataSizeUnit;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserCreateForm {



    private String username;

    private String realName;

    private String signupDate;


    private String password1;


    private String password2;
}
