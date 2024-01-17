package com.example.attendance.controller.api.annual;


import com.example.attendance.entity.Annual;
import com.example.attendance.entity.SiteUser;
import com.example.attendance.service.AnnualService;
import com.example.attendance.service.AttendanceService;
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
public class AnnualController {

    private final UserService userService;

    private final AnnualService annualService;

    private final AttendanceService attendanceService;


    //---------------------------연차신청-----------------------------------//
    @PostMapping("/annual/enroll")
    public ResponseEntity<String> annualEnroll(@RequestBody Map<String, Object> requestBody, Principal principal) {

        try {
            String reason = (String) requestBody.get("reason");
            String annualDateTemp = (String) requestBody.get("annualDate");
            // Js 에서 받은 데이터를 String 으로 변환

            SiteUser user = userService.findUser(principal.getName());
            //접속자정보로 유저정보 검색


            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime annualDate = LocalDateTime.parse(annualDateTemp, formatter);
            //fomatter 를 이용하여 받은 String 값 LocalDate 로 변환.
            if (user.getAnnualCount() > 0 || user.getYearAnnalCount() > 0) {
                try {
                    this.annualService.annualEnroll(user, annualDate, reason);
                    return ResponseEntity.ok(annualDate + "일자 연차 신청이 완료 되었습니다.");


                } catch (Exception e) {
                    return ResponseEntity.badRequest().body(e + "연차 신청에 실패했습니다.");
                }
            }else {
                return ResponseEntity.ok("사용할 수 있는 휴가가 없습니다. ");
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e+"처리중 에러가 발생했습니다.");
        }

    }


    //---------------------------연차신청 취소-----------------------------------//
    @GetMapping("/annual/cancel/{id}")
    public ResponseEntity<String> annualCancel(Principal principal, @PathVariable("id")String id) {
        SiteUser user = userService.findUser(principal.getName());
        //접속자정보로 유저정보 검색
        Annual annual = this.annualService.getAnnualOne(id);
        //연차신청 id 정보로 연차신청내역 검색
        try {
            if (user.getUsername().equals(annual.getUser().getUsername())) {
                this.annualService.annualCancel(annual);
                return ResponseEntity.ok("연차신청 취소가 완료 되었습니다.");
            } else {
                return ResponseEntity.ok("해당 연차의 신청자가 아닙니다.");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("오류가 발생했습니다. 오류::" + e);
        }
    }


        //---------------------------연차승인 -----------------------------------//
        @GetMapping("/admin/annual/enroll/ok/{id}")
        public ResponseEntity<String> annualEnrollOk(Principal principal, @PathVariable("id")String id){
            SiteUser user = userService.findUser(principal.getName());
            //접속자정보로 유저정보 검색
            Annual annual = this.annualService.getAnnualOne(id);
            //연차신청 id 정보로 연차신청내역 검색
            try {
                    this.annualService.annualEnrollOk(annual,user);
                    this.attendanceService.annualEnrollOk(annual);
                    this.userService.usedAnnual(annual);
                    return ResponseEntity.ok("연차가 승인 되었습니다.");

            }catch (Exception e){
                return ResponseEntity.badRequest().body("오류가 발생했습니다. 오류::"+e);
            }

        }

    //---------------------------연차반려 -----------------------------------//

    @GetMapping("/admin/annual/enroll/no/{id}")
    public ResponseEntity<String> annualEnrollNo(Principal principal, @PathVariable("id")String id){
        SiteUser user = userService.findUser(principal.getName());
        //접속자정보로 유저정보 검색
        Annual annual = this.annualService.getAnnualOne(id);
        //연차신청 id 정보로 연차신청내역 검색
        try {
            this.annualService.annualEnrollNo(annual,user);
            return ResponseEntity.ok("연차가 반려 되었습니다.");

        }catch (Exception e){
            return ResponseEntity.badRequest().body("오류가 발생했습니다. 오류::"+e);
        }

    }
}