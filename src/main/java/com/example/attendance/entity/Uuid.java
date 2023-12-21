package com.example.attendance.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("uuid")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class Uuid {
    // 유저의 고유번호 저장(중복방지 )


    @Id

    private String id;
    //고유번호

    @DBRef
    private SiteUser user;
    //연결된 user
}
