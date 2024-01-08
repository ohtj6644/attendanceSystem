package com.example.attendance.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Document("annual")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties("site_User")
public class Annual {
    //연차신청


    @Id
    private String id;

    private  SiteUser user;
    //휴가 신청인

    private LocalDate annualDate;
    //휴가 희망일

    private LocalDateTime createDate;
    //신청일

    private boolean approval;
    // 승인여부

    private SiteUser approvalUser;
    // 휴가 승인인원

    private String reason;
    //휴가 신청사유

}
