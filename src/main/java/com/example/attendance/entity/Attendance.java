package com.example.attendance.entity;


import lombok.*;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Document("attendance")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Attendance {
    // 근태 엔티티 //

    @Id
    private String id;


    @DBRef
    private  SiteUser user;

    private LocalDateTime startWorkTime;
    //출근시간
    private LocalDateTime endWorkTime;
    //퇴근시간


    private int workTime;
    //근무 시간 (단위 : 분)

    private int Annual=0;
    //휴가여부


}
