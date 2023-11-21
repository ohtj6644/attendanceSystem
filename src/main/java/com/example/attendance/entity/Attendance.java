package com.example.attendance.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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


}
