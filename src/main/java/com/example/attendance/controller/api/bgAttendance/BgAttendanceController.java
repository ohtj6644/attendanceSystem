package com.example.attendance.controller.api.bgAttendance;


import com.example.attendance.entity.SiteUser;
import com.example.attendance.service.BgAttendanceService;
import com.example.attendance.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    //---------------------------연차신청-----------------------------------//
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
}
