package com.example.attendance.controller.api.user;


import com.example.attendance.service.UserService;
import com.example.attendance.entity.SiteUser;
import com.example.attendance.user.UserCreateForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
public class UserController {

    private  final UserService userService;


    //---------------------------유저생성---------------------------////
    @PostMapping("/admin/signup")
    public ResponseEntity<String> signupUser(@ModelAttribute UserCreateForm userCreateForm) {

        try {
            // 패스워드 일치 여부 확인
            if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
                return ResponseEntity.badRequest().body("패스워드 불일치");
            }

            if (this.userService.findUser(userCreateForm.getUsername()) != null) {
                return ResponseEntity.badRequest().body("이미 존재하는 id입니다");
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            LocalDate signupDate = LocalDate.parse(userCreateForm.getSignupDate(), formatter);
            LocalDateTime signupDateTime = signupDate.atStartOfDay();

            // 유저 생성
            SiteUser user = this.userService.newUser(
                    userCreateForm.getUsername(),
                    userCreateForm.getPassword1(),
                    userCreateForm.getRealName(),
                    signupDateTime
            );

            // JSON 응답 반환
            return ResponseEntity.ok("유저 생성 완료. 유저번호:" + user.getUuid());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("회원가입에 실패하였습니다");
        }
    }


    //---------------------로그아웃--------------------//
    @GetMapping("user/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.removeAttribute("loggedIn");

        return ResponseEntity.ok("로그아웃 되었습니다 ");
    }

    //---------------------관리자권한 추가 --------------------//
    @GetMapping("/admin/user/grantup/{id}")
    public ResponseEntity<String> grantUp(@PathVariable("id")String id){

        try{
            this.userService.grantUp(id);
            return ResponseEntity.ok(id+"번 유저에게 관리자 권한이 추가되었습니다.");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("권한부여 실패 ");
        }

    }

    //---------------------관리자권한 회수 --------------------//
    @GetMapping("/admin/user/grantdown/{id}")
    public ResponseEntity<String> grantDown(@PathVariable("id")String id){

        try{
            this.userService.grantDown(id);
            return ResponseEntity.ok(id+"번 유저에게 관리자 권한이 회수되었습니다.");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("권한회수 실패 ");
        }

    }



    //--------------------유저 테스트데이터 생성---------------------//
    @GetMapping("admin/test/userPlus")
    public String userPlus(){

        for(int i=0; i<90; i++){
            this.userService.newUser("userTest"+i,"1234","테스트유저"+i, LocalDateTime.now());
        }
        return "테스트유저 생성완료";
    }

    //--------------------프로필 업데이트---------------------//
    @PostMapping("/profile/upload")
    public ResponseEntity<String> profileUpload(@RequestPart("file") MultipartFile file,Principal principal) {

        SiteUser user = this.userService.findUser(principal.getName());
        try {
            // static/file/profile 경로 설정
            Path uploadPath = Paths.get("static/file/profile/");

            // 경로에 디렉토리가 존재하지 않으면 생성
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String randomFileName = generateRandomFileName(12);

            // 확장자를 포함한 새로운 파일 이름
            String newFileName = randomFileName + getFileExtension(file.getOriginalFilename());


            // 업로드할 파일의 경로를 설정
            Path filePath = uploadPath.resolve(newFileName);

            // 파일을 지정된 경로로 저장
            Files.write(filePath, file.getBytes());

            String avatarUrl = "/file/profile/"+newFileName;
            this.userService.setUserAvatarUrl(user,avatarUrl);


            // 성공적인 응답 반환
            return ResponseEntity.ok("프로필 사진이 변경되었습니다");
        } catch (Exception e) {
            e.printStackTrace();
            // 에러 발생 시 실패 응답 반환
            return  ResponseEntity.badRequest().body("프로필사진 변경 오류 (관리자에게 문의 하세요)");
        }
    }

    //--------------------파일 이름을 12자리 랜덤 문자열로 생성---------------------//
    private String generateRandomFileName(int length) {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, length);
    }

    //--------------------파일의 확장자를 반환---------------------//드
    private String getFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf(".");
        return lastDotIndex == -1 ? "" : fileName.substring(lastDotIndex);
    }



}
