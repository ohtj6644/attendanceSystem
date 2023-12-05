package com.example.attendance.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Notice")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Notice {
    // 공지사항 엔티티


    @Id
    private String id;
    private String content;
    //게시글 내용

    @DBRef
    private  SiteUser author;
    //작성자


}
