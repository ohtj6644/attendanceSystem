package com.example.attendance.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document("companyFile")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyFile {


    @Id
    private String id;

    private String fileName;
    //  문서 이름

    private LocalDate createDate;
    // 문서 생성 일자

    private String fileUrl;
    // 파일 경로

}
