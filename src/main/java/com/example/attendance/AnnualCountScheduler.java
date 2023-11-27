package com.example.attendance;


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

    // 매월 1일 0시에 실행
    @Scheduled(cron = "0 0 0 * * ?")
    public void updateAnnualCount() {
        List<SiteUser> users = siteUserService.getAllUsers();

        for (SiteUser user : users) {
            LocalDateTime signupDate = user.getSignupDate();
            LocalDateTime now = LocalDateTime.now();

            // 가입일로부터 한 달이 지났을 경우 AnnualCount를 증가
            if (Duration.between(signupDate, now).toDays() >= 30) {
                int currentAnnualCount = user.getAnnualCount();
                user.setAnnualCount(currentAnnualCount + 1);
                siteUserService.saveUser(user);
            }
        }
    }
}
