package com.example.attendance.controller.Scheduler;


import com.example.attendance.entity.SiteUser;
import com.example.attendance.service.UserService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class AnnualCountScheduler {

    //연차생성스케줄러 컴포넌트 클래스 //

    private final UserService siteUserService;

    public AnnualCountScheduler(UserService siteUserService) {
        this.siteUserService = siteUserService;
    }


    //---------------------------휴가(월차)/ 년차 생성--------------------//
    // 매월 자정에 실행
    @Scheduled(cron = "0 0 0 * * ?")
    public void updateAnnualCount() {
        List<SiteUser> users = siteUserService.getAllUsers();

        for (SiteUser user : users) {
            LocalDateTime signupDate = user.getSignupDate();
            LocalDateTime now = LocalDateTime.now();
            // 전체 유저정보를 불러옴.

            // 가입일로부터 1년 미만일 경우  한 달이 지날때마다  AnnualCount를 증가 (월차)
            if (Duration.between(signupDate, now).toDays() <= 365) {
                int days = (int) Duration.between(signupDate, now).toDays()/30;
                //30일당 한개씩 휴가 생성.

                user.setAnnualCount(days);

            }else {
                int yearCount=(int) Duration.between(signupDate, now).toDays()/365;
                //가입일로부터 년수 체크


                user.setYearAnnalCount(yearCount*15);
                //1년마다 15개의 연차를 지급하고

            }
            siteUserService.saveUser(user);
        }
    }



}

//연차 부여로직은 가입일 기준으로 수정예정
