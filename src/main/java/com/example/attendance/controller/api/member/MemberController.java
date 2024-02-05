package com.example.attendance.controller.api.member;


import com.example.attendance.entity.SiteUser;
import com.example.attendance.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final UserService userService;


    @GetMapping("/member/search/{keyword}")
    public ResponseEntity<List<SiteUser>> memberSearch(@PathVariable("keyword")String keyword){

        List<SiteUser> memberList=this.userService.getSearchList(keyword);
        return ResponseEntity.ok(memberList);

    }


}
