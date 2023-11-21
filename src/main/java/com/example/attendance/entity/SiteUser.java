package com.example.attendance.entity;


import com.example.attendance.entity.Uuid;
import com.example.attendance.user.UserRole;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


import java.time.LocalDateTime;

@Document("site_user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class SiteUser {

    @Id
    private String uuid;
    //회원번호 (UUID 의 id 값으로 받아옴 )

    private String username;
    //회원 로그인 id

    private String password;
    //회원 비밀번호

    private LocalDateTime signupDate;
    //가입일시


    private UserRole role=UserRole.USER;

    @DBRef
    private Uuid Uuid_id;
    //고유번호


}
