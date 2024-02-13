package com.example.attendance.service;

import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class FileService {



    //--------------------파일 이름을 12자리 랜덤 문자열로 생성---------------------//
    public String generateRandomFileName(int length) {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, length);
    }


    //--------------------파일의 확장자를 반환---------------------//드
   public String getFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf(".");
        return lastDotIndex == -1 ? "" : fileName.substring(lastDotIndex);
    }
}
