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

                int useAnnualCount = user.getUseAnnal();
                user.setAnnualCount(days-useAnnualCount );
                //생성한 총휴가수에 사용한 휴가 개수를 뺀 값을 저장.

                siteUserService.saveUser(user);
            }else {
                int yearCount=(int) Duration.between(signupDate, now).toDays()/365;
                //가입일로부터 년수 체크

                int useYearAnnualCount = user.getUseYearAnnal();
                user.setYearAnnalCount((yearCount*12)-useYearAnnualCount);
                //1년마다 12개의 연차를 지급하고 / 해당 연차총 개수 에서 사용한 연차를 뺀 나머지 연차를 저장.

            }
        }
    }



}
