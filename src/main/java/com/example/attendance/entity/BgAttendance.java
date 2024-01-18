package com.example.attendance.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Document("bgAttendance")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties("site_user")
public class BgAttendance {

    @Id
    private String id;

    private  SiteUser user;
    //외근 신청인

    private LocalDate BgDate;
    //외근 희망일

    private LocalDateTime createDate;
    //신청일

    private String approval;
    // 승인상태 : 신청/승인/반려/취소

    private SiteUser approvalUser;
    // 외근 승인 인원

    private String area;
    //휴가 외근지역

}
