package com.example.attendance.controller.api.annual;


import com.example.attendance.entity.SiteUser;
import com.example.attendance.service.AnnualService;
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
public class AnnualController {

    private final UserService userService;

    private final AnnualService annualService;


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
}