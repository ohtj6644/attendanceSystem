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

    private String username;

    private String password;

    private LocalDateTime signupDate;

    private UserRole role;

    @DBRef
    private Uuid Uuid_id;


}
