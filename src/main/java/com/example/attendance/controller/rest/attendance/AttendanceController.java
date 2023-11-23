package com.example.attendance.controller.rest.attendance;


import com.example.attendance.entity.SiteUser;
import com.example.attendance.service.AttendanceService;
import com.example.attendance.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;
    private final UserService userService;

    // 근무 시작  //
    @GetMapping("/user/startWork")
    public String startWork(Principal principal){
        SiteUser user=userService.findUser(principal.getName());
        // 이미 근무시작을 하였는지 확인.
        if(attendanceService.getNowAttendance()==null){
            return "이미 오늘 근무시작을 하였습니다.";

        // 근무시작을 안했을 경우 아래 메서드로 근무 생성
        }else {
            String workMessage= attendanceService.startWork(user);
            return workMessage+" 근무 시작 ";
        }

    }

}
