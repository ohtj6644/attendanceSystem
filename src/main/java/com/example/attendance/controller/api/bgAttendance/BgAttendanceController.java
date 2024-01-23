package com.example.attendance.controller.api.bgAttendance;


import com.example.attendance.entity.Annual;
import com.example.attendance.entity.BgAttendance;
import com.example.attendance.entity.SiteUser;
import com.example.attendance.service.AttendanceService;
import com.example.attendance.service.BgAttendanceService;
import com.example.attendance.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class BgAttendanceController {

    private final BgAttendanceService bgService;

    private final UserService userService;

    private final AttendanceService attendanceService;

    //---------------------------외근신청-----------------------------------//
    @PostMapping("/bgAttendance/enroll")
    public ResponseEntity<String> annualEnroll(@RequestBody Map<String, Object> requestBody, Principal principal) {


        try {
            Object areaObject = requestBody.get("area");
            Object bgDateObject = requestBody.get("bgDate");

            // 여기서 원하는 타입으로 변환하거나 처리
            String area = areaObject != null ? areaObject.toString() : null;
            String bgDateTemp = bgDateObject != null ? bgDateObject.toString() : null;

            // Js 에서 받은 데이터를 String 으로 변환

            SiteUser user = userService.findUser(principal.getName());
            //접속자정보로 유저정보 검색


            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate bgDate = LocalDate.parse(bgDateTemp, formatter);
            //fomatter 를 이용하여 받은 String 값 LocalDate 로 변환.
                try {
                    this.bgService.bgEnroll(user, bgDate, area);
                    return ResponseEntity.ok(bgDate+"일자 외근 신청이 완료 되었습니다.");


                } catch (Exception e) {
                    return ResponseEntity.badRequest().body(e + "외근 신청에 실패했습니다.");
                }

        }catch (Exception e){
            return ResponseEntity.badRequest().body(e+"처리중 에러가 발생했습니다.");
        }

    }

    ///user/bg/cancel/
    //---------------------------외근신청취소-----------------------------------//
    @GetMapping("/user/bg/cancel/{id}")
    public ResponseEntity<String> bgCancel(Principal principal, @PathVariable("id")String id) {
        SiteUser user = userService.findUser(principal.getName());
        //접속자정보로 유저정보 검색
        BgAttendance bg = this.bgService.getBgOne(id);
        //연차신청 id 정보로 연차신청내역 검색
        try {
            if (user.getUsername().equals(bg.getUser().getUsername())) {
                this.bgService.bgCancel(bg);
                return ResponseEntity.ok("연차신청 취소가 완료 되었습니다.");
            } else {
                return ResponseEntity.ok("해당 연차의 신청자가 아닙니다.");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("오류가 발생했습니다. 오류::" + e);
        }
    }



    //---------------------------외근승인 -----------------------------------//
    @GetMapping("/admin/bg/attendance/ok/{id}")
    public ResponseEntity<String> annualEnrollOk(Principal principal, @PathVariable("id")String id){
        SiteUser user = userService.findUser(principal.getName());
        //접속자정보로 유저정보 검색
        BgAttendance bgAttendance = this.bgService.getBgOne(id);
        //연차신청 id 정보로 연차신청내역 검색
        try {
            this.bgService.bgEnrollOk(bgAttendance,user);
            this.attendanceService.bgEnrollOk(bgAttendance);
            return ResponseEntity.ok("외근이 승인 되었습니다.");

        }catch (Exception e){
            return ResponseEntity.badRequest().body("오류가 발생했습니다. 오류::"+e);
        }

    }

    //---------------------------연차반려 -----------------------------------//

    @GetMapping("/admin/bg/attendance/no/{id}")
    public ResponseEntity<String> annualEnrollNo(Principal principal, @PathVariable("id")String id){
        SiteUser user = userService.findUser(principal.getName());
        //접속자정보로 유저정보 검색
        BgAttendance bgAttendance= this.bgService.getBgOne(id);
        //연차신청 id 정보로 연차신청내역 검색
        try {
            this.bgService.bgEnrollNo(bgAttendance,user);
            return ResponseEntity.ok("연차가 반려 되었습니다.");

        }catch (Exception e){
            return ResponseEntity.badRequest().body("오류가 발생했습니다. 오류::"+e);
        }

    }
}
