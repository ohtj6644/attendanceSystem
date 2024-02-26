package com.example.attendance.controller.api.companyFile;


import com.example.attendance.common.ThumbnailGenerator;
import com.example.attendance.entity.CompanyFile;
import com.example.attendance.entity.SiteUser;
import com.example.attendance.service.CompanyFileService;
import com.example.attendance.service.FileService;
import com.example.attendance.service.UserService;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CompanyFileController {

    private final UserService userService;

    private final FileService fileService;

    private final CompanyFileService companyFileService;

    @Autowired
    private ThumbnailGenerator thumbnailGenerator;


    //--------------------회사 문서 추가---------------------//
//    @PostMapping("/admin/companyFile/upload")
//    public ResponseEntity<String> companyFileUpload(@RequestPart("file") MultipartFile file, @RequestPart("text") String fileName, Principal principal) {
//
//        try {
//            // static/file/profile 경로 설정
//            Path uploadPath = Paths.get("static/file/companyFile/");
//
//            // 경로에 디렉토리가 존재하지 않으면 생성
//            if (!Files.exists(uploadPath)) {
//                Files.createDirectories(uploadPath);
//            }
//
//            String randomFileName = this.fileService.generateRandomFileName(12);
//
//            // 확장자를 포함한 새로운 파일 이름
//            String newFileName = randomFileName + this.fileService.getFileExtension(file.getOriginalFilename());
//
//
//            // 업로드할 파일의 경로를 설정
//            Path filePath = uploadPath.resolve(newFileName);
//
//            // 파일을 지정된 경로로 저장
//            Files.write(filePath, file.getBytes());
//
//            String fileUrl = "/file/companyFile/" + newFileName;
//            this.companyFileService.createCompanyFile(fileName, fileUrl);
//
//
//            // 성공적인 응답 반환
//            return ResponseEntity.ok("새 문서가 저장 되었습니다.");
//        } catch (Exception e) {
//            e.printStackTrace();
//            // 에러 발생 시 실패 응답 반환
//            return ResponseEntity.badRequest().body("회서문서 등록오류 (관리자에게 문의 하세요)");
//        }
//    }

    @PostMapping("/admin/companyFile/upload")
    public ResponseEntity<String> companyFileUpload(@RequestPart("file") MultipartFile file,
                                                    @RequestPart("text") String fileName,
                                                    Principal principal) {
        try {
            // static/file/companyFile 경로 설정
            Path uploadPath = Paths.get("static", "/file/companyFile/");

            // 경로에 디렉토리가 존재하지 않으면 생성
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // 파일 이름을 무작위로 생성
            String randomFileName = this.fileService.generateRandomFileName(12);

            // 업로드할 파일의 경로를 설정
            Path filePath = uploadPath.resolve(randomFileName + this.fileService.getFileExtension(file.getOriginalFilename()));

            // 파일을 지정된 경로로 저장
            Files.write(filePath, file.getBytes());

            // 파일 형식에 따라 썸네일 생성
            String fileExtension = this.fileService.getFileExtension(file.getOriginalFilename());
            String thumbnailUrl; // 초기화

            if (".pdf".equalsIgnoreCase(fileExtension)) {
                // PDF 파일의 경우 썸네일 생성
                String thumbnailFileName = randomFileName + ".png";
                String thumbnailFilePath = uploadPath.resolve(thumbnailFileName).toString();
                thumbnailGenerator.generatePdfThumbnail(filePath.toString(), thumbnailFilePath);

                // 썸네일 경로 설정
                thumbnailUrl = "/file/companyFile/" + thumbnailFileName;
            } else if (isImageFile(fileExtension)) {
                String thumbnailFileName = randomFileName + "_thumbnail.png";
                String thumbnailFilePath = uploadPath.resolve(thumbnailFileName).toString();
                generateImageThumbnail(filePath.toString(), thumbnailFilePath);

                // 썸네일 경로 설정
                thumbnailUrl = "/file/companyFile/" + thumbnailFileName;
            } else {
                // 지원되지 않는 파일 형식의 경우
                String fileExtension1 = this.fileService.getFileExtension(file.getOriginalFilename());
                System.out.println("File Extension: " + fileExtension1);
                return ResponseEntity.ok("pdf, jpg, png 만 등록이 가능합니다");
            }

            // 파일 및 썸네일 정보를 데이터베이스에 저장
            this.companyFileService.createCompanyFile(fileName, filePath.toString(), thumbnailUrl);

            // 성공적인 응답 반환
            return ResponseEntity.ok("새 문서가 저장 되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            // 에러 발생 시 실패 응답 반환
            return ResponseEntity.badRequest().body("회서문서 등록오류 (관리자에게 문의 하세요)");
        }
    }

    private boolean isImageFile(String fileExtension) {
        // 이미지 파일 확장자 목록 (jpg, jpeg, png 등 추가 가능)
        List<String> imageExtensions = Arrays.asList(".jpg", ".jpeg", ".png");
        return imageExtensions.contains(fileExtension.toLowerCase());
    }

    private void generateImageThumbnail(String sourcePath, String destinationPath) throws IOException {
        // 이미지 파일의 경우 Thumbnailator 라이브러리를 사용하여 썸네일 생성
        Thumbnails.of(sourcePath)
                .size(210, 300)
                .crop(Positions.CENTER)
                .outputFormat("png")
                .toFile(destinationPath);
    }

    //--------------------회사 문서 삭제---------------------//
    @GetMapping("/admin/companyFile/delete/{id}")
    public ResponseEntity<String> companyFileDelete(@PathVariable("id")String id){
        CompanyFile companyFile = this.companyFileService.getCompanyFile(id);
        String fileUrl = companyFile.getFileUrl();

        if (fileUrl != null && !fileUrl.isEmpty()) {
            try {
                // 파일 경로를 사용하여 Path 객체 생성
                Path filePath = Paths.get("static", fileUrl);
                // first 에 상위폴더를 지정해줌

                // 파일이 존재하면 삭제
                if (Files.exists(filePath)) {
                    try {
                        Files.delete(filePath);

                        // 파일 삭제 성공
                        this.companyFileService.companyFileDelete(companyFile);
                        return ResponseEntity.ok("문서 및 파일이 삭제되었습니다.");
                    }catch (Exception e){
                        return ResponseEntity.ok(e+"삭제실패 오류");
                    }
                    // 파일 삭제 시도

                } else {
                    // 파일이 존재하지 않음
                    return ResponseEntity.ok("파일을 찾을 수 없습니다. (상대 경로: " + fileUrl + ")");
                }
            } catch (Exception e) {
                // 예외 발생 시
                e.printStackTrace(); // 디버깅을 위해 이 줄을 추가합니다.
                return ResponseEntity.ok("파일 삭제 중 오류 발생");
            }
        } else {
            // fileUrl이 비어있는 경우
            return ResponseEntity.ok("유효한 파일 경로가 없습니다.");
        }



    }


}
