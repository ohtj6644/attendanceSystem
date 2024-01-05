package com.example.attendance.entity;


import com.example.attendance.entity.Uuid;
import com.example.attendance.user.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


import java.time.LocalDateTime;
import java.util.List;

@Document("site_user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@JsonIgnoreProperties("attendance")
public class SiteUser {

    @Id
    private String uuid;
    //회원번호 (UUID 의 id 값으로 받아옴 )

    private String username;
    //회원 로그인 id

    private String password;
    //회원 비밀번호

    private String realName;

    private LocalDateTime signupDate;
    //가입일시 (해당 데이터 기준으로 연차생성)


    private UserRole role=UserRole.USER;
    //회원 권한 (초기값 user 로 셋팅 )

    @DBRef
    @JsonIgnore
    private Uuid Uuid_id;
    //고유번호

    @DBRef
    @JsonIgnore
    private List<Attendance> attendances;
    //근태



    @DBRef
    @JsonIgnore
    private List<Notice> notices;


    private int AnnualCount;
    //잔여 휴가 개수

    private int useAnnal;
    //사용한 휴가 개수

    private int YearAnnalCount;
    //잔여 연차 개수

    private int useYearAnnal;
    //사용한 연차 개수


}
