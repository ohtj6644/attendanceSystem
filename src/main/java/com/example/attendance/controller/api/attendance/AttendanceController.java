package com.example.attendance.controller.api.attendance;


import com.example.attendance.entity.Attendance;
import com.example.attendance.entity.SiteUser;
import com.example.attendance.service.AttendanceService;
import com.example.attendance.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;
    private final UserService userService;

    //------------------------근무 시작-------------------  //
    @GetMapping("/user/startWork")
    public ResponseEntity<String> startWork(Principal principal) {
        SiteUser user = userService.findUser(principal.getName());

        // 이미 근무 시작을 하였는지 확인
        if (attendanceService.getNowAttendance(user) != null) {
            return ResponseEntity.badRequest().body("이미 오늘의 근무가 있습니다.");
        } else {
            String workMessage = attendanceService.startWork(user);
            return ResponseEntity.ok(workMessage + " 근무 시작");
        }
    }

    // ---------------------근무 종료-----------------------//
    @PutMapping("/user/endWork")
    public ResponseEntity<String> endWork(Principal principal){
        SiteUser user=userService.findUser(principal.getName());


        //시작한 근무가 없으면 메시지 반환
        if(attendanceService.getNowAttendance(user)==null){
            return ResponseEntity.badRequest().body("종료할 근무가 없습니다. ");
        }else  if(attendanceService.getNowAttendance(user).getEndWorkTime()!=null){
            return ResponseEntity.badRequest().body("이미 오늘 근무가 종료되었습니다 ");
        } else {
            String woriMessage = attendanceService.endWork(attendanceService.getNowAttendance(user));
            return ResponseEntity.ok(woriMessage+" 근무 종료");

        }


    }


    @PostMapping("/user/monthAttendance/")
    public ResponseEntity<Page<Attendance>> getSearchAttendance(@RequestParam(value = "startDate")LocalDateTime startDate , @RequestParam(value = "endDate")LocalDateTime endDate, @RequestParam(value = "page" , defaultValue = "0")int page, Principal principal){

        SiteUser user=userService.findUser(principal.getName());
        Page<Attendance> AttendanceList = this.attendanceService.getSearchAttendance(startDate, endDate,user,page);
        return ResponseEntity.ok(AttendanceList);
    }






}
