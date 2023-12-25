package com.example.attendance.controller.api.attendance;


import com.example.attendance.entity.Attendance;
import com.example.attendance.entity.SiteUser;
import com.example.attendance.service.AttendanceService;
import com.example.attendance.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

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

    // ---------------------근무 조회 -----------------------//
    @PostMapping("/user/monthAttendance/")
    public ResponseEntity<?> getSearchAttendance(
            @RequestBody Map<String, Object> requestBody,
            Principal principal) {

        String startDateString = (String) requestBody.get("startDate");
        String endDateString = (String) requestBody.get("endDate");
        // Js 에서 받은 데이터를 String 으로 변환

        SiteUser user = userService.findUser(principal.getName());
        //접속자정보로 유저정보 검색

        System.out.println("=========근무내역검색:"+startDateString+"~");
        System.out.println("~"+endDateString+"열람자:"+user.getUsername()+"========");
        // 받은 데이터 정상여부 확인하려고 데이터 출력 만듬

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDate startDate = LocalDate.parse(startDateString,formatter);
        LocalDate endDate = LocalDate.parse(endDateString,formatter);
        //fomatter 를 이용하여 받은 String 값 LocalDate 로 변환.

        if (startDate.plusMonths(3).isBefore(endDate)) {
            System.out.println("-----------검색기간 초과 error-------------- ");
            return ResponseEntity.badRequest().body("검색 기간은 최대 3달까지만 허용됩니다.");
        }else {
            Page<Attendance> attendanceList = this.attendanceService.getSearchAttendance(startDate, endDate, user, 0);
            // 유저정보 , 검색기간 , 페이지 수 를 서비스로 넘겨서 해당 정보에 맞는 근무리스트를 Page 로 가져옴
            return ResponseEntity.ok(attendanceList);
        }

    }





}
