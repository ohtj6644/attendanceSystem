package com.example.attendance.controller.api.companyFile;


import com.example.attendance.entity.CompanyFile;
import com.example.attendance.entity.SiteUser;
import com.example.attendance.service.CompanyFileService;
import com.example.attendance.service.FileService;
import com.example.attendance.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CompanyFileController {

    private final UserService userService;

    private final FileService fileService;

    private final CompanyFileService companyFileService;


    //--------------------회사 문서 추가---------------------//
    @PostMapping("/admin/companyFile/upload")
    public ResponseEntity<String> companyFileUpload(@RequestPart("file") MultipartFile file, @RequestPart("text") String fileName, Principal principal) {

        try {
            // static/file/profile 경로 설정
            Path uploadPath = Paths.get("static/file/companyFile/");

            // 경로에 디렉토리가 존재하지 않으면 생성
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String randomFileName = this.fileService.generateRandomFileName(12);

            // 확장자를 포함한 새로운 파일 이름
            String newFileName = randomFileName + this.fileService.getFileExtension(file.getOriginalFilename());


            // 업로드할 파일의 경로를 설정
            Path filePath = uploadPath.resolve(newFileName);

            // 파일을 지정된 경로로 저장
            Files.write(filePath, file.getBytes());

            String fileUrl = "/file/companyFile/" + newFileName;
            this.companyFileService.createCompanyFile(fileName, fileUrl);


            // 성공적인 응답 반환
            return ResponseEntity.ok("새 문서가 저장 되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            // 에러 발생 시 실패 응답 반환
            return ResponseEntity.badRequest().body("회서문서 등록오류 (관리자에게 문의 하세요)");
        }
    }


    //--------------------회사 문서 삭제---------------------//
    @GetMapping("/admin/companyFile/delete/{id}")
    public ResponseEntity<String> companyFileDelete(@PathVariable("id")String id){
        CompanyFile companyFile = this.companyFileService.getCompanyFile(id);
        String fileUrl = companyFile.getFileUrl();

        if (fileUrl != null && !fileUrl.isEmpty()) {
            try {
                // 파일 경로를 사용하여 파일 객체 생성
                File fileToDelete = new File(fileUrl);

                // 파일이 존재하면 삭제
                if (fileToDelete.exists()) {
                    if (fileToDelete.delete()) {
                        // 파일 삭제 성공
                        this.companyFileService.companyFileDelete(companyFile);
                        return ResponseEntity.ok("문서 및 파일이 삭제되었습니다.");
                    } else {
                        // 파일 삭제 실패
                        return ResponseEntity.ok("파일 삭제 실패");
                    }
                } else {
                    // 파일이 존재하지 않음
                    return ResponseEntity.ok("파일을 찾을 수 없습니다.");
                }
            } catch (Exception e) {
                // 예외 발생 시
                return ResponseEntity.ok("파일 삭제 중 오류 발생");
            }
        } else {
            // fileUrl이 비어있는 경우
            return ResponseEntity.ok("유효한 파일 경로가 없습니다.");
        }



    }


}
