package com.example.attendance.controller.view;


import com.example.attendance.entity.*;
import com.example.attendance.service.*;
import com.example.attendance.user.UserCreateForm;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.time.YearMonth;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class viewController {
    // 화면(view) 반환 컨트롤러 //

    private final UserService userService;
    private final AttendanceService attendanceService;
    private final NoticeService noticeService;
    private final AnnualService annualService;

    private final BgAttendanceService bgService;


    //-----------------유저 로그인 화면 반환---------------------//
    @GetMapping("/user/login")
    public String login(){
        return "login_form";
    }

    //-----------------------유저 로그인 ---------------------------------//
    @PostMapping("/user/login")
    public String login(Model model, HttpSession session, @RequestParam("username") String username, @RequestParam("password") String password) {
        if (userService.authenticateUser(username, password)) {
            session.setAttribute("loggedIn", true);

            SiteUser user = this.userService.findUser(username);
            session.setAttribute("user", user);

            return "redirect:/";
        } else {
            model.addAttribute("error", true);
            return "login";
        }
    }

    //----------------------메인페이지 반환 ---------------------------//

    @GetMapping("/")
    @PreAuthorize("isAuthenticated()")
    // 로그인이 안된 유저는 메인페이지 접근 시 로그인페이지로 반환.
    public String mainPage(Model model , Principal principal){

        boolean todayState ;

        SiteUser user = userService.findUser(principal.getName());
        List<Notice> noticeList = this.noticeService.getList();

        if(attendanceService.getNowAttendance(user)==null){
            todayState = false;
        }else if ((attendanceService.getNowAttendance(user) != null) & (attendanceService.getNowAttendance(user).getEndWorkTime()== null) ) {
            todayState = true;
            model.addAttribute("startWorkTime",attendanceService.getNowAttendance(user).getStartWorkTime());

        }else if((attendanceService.getNowAttendance(user) != null) & (attendanceService.getNowAttendance(user).getEndWorkTime()!= null)){
            todayState = false;
        }else {
            todayState = false;
        }

        model.addAttribute("noticeList",noticeList);
        model.addAttribute("todayState",todayState);
        model.addAttribute("user",user);


        return "main_Page";
    }



    //---------------------- 나의 근무 내역 --------------------------//
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/user/monthAttendance/")
    public String getMonthAttendance(Model model, Principal principal){
        SiteUser user= this.userService.findUser(principal.getName());


        boolean todayState ;
        if(attendanceService.getNowAttendance(user)==null){
            todayState = false;
        }else if ((attendanceService.getNowAttendance(user) != null) & (attendanceService.getNowAttendance(user).getEndWorkTime()== null) ) {
            todayState = true;
            model.addAttribute("startWorkTime",attendanceService.getNowAttendance(user).getStartWorkTime());

        }else if((attendanceService.getNowAttendance(user) != null) & (attendanceService.getNowAttendance(user).getEndWorkTime()!= null)){
            todayState = false;
        }else {
            todayState = false;
        }

        model.addAttribute("todayState",todayState);
        model.addAttribute("user",user);


        return "attendance/user_attendance";
    }




    //---------------------- 휴가신청 --------------------------//
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/user/annual/enroll")
    public String annualRequest(Model model, Principal principal){
        SiteUser user= this.userService.findUser(principal.getName());

        boolean todayState ;
        if(attendanceService.getNowAttendance(user)==null){
            todayState = false;
        }else if ((attendanceService.getNowAttendance(user) != null) & (attendanceService.getNowAttendance(user).getEndWorkTime()== null) ) {
            todayState = true;
            model.addAttribute("startWorkTime",attendanceService.getNowAttendance(user).getStartWorkTime());

        }else if((attendanceService.getNowAttendance(user) != null) & (attendanceService.getNowAttendance(user).getEndWorkTime()!= null)){
            todayState = false;
        }else {
            todayState = false;
        }

        model.addAttribute("todayState",todayState);
        model.addAttribute("user",user);
        return "annual/user_annual_create";
    }


    //---------------------- 휴가신청내역 --------------------------//
    @GetMapping("/user/annual/enroll/list")
    public String enrollRequest(Model model , Principal principal,@RequestParam(value = "page",defaultValue = "0")int page){
        SiteUser user= this.userService.findUser(principal.getName());
        Page<Annual> annualList=this.annualService.getUserList(user,page);

        boolean todayState ;
        if(attendanceService.getNowAttendance(user)==null){
            todayState = false;
        }else if ((attendanceService.getNowAttendance(user) != null) & (attendanceService.getNowAttendance(user).getEndWorkTime()== null) ) {
            todayState = true;
            model.addAttribute("startWorkTime",attendanceService.getNowAttendance(user).getStartWorkTime());

        }else if((attendanceService.getNowAttendance(user) != null) & (attendanceService.getNowAttendance(user).getEndWorkTime()!= null)){
            todayState = false;
        }else {
            todayState = false;
        }

        model.addAttribute("todayState",todayState);
        model.addAttribute("user",user);
        model.addAttribute("paging",annualList);
        return "annual/user_annual_enroll_list";
    }


    //---------------------- 외근신청 --------------------------//
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/user/bg/enroll")
    public String bgRequest(Model model, Principal principal){
        SiteUser user= this.userService.findUser(principal.getName());

        boolean todayState ;
        if(attendanceService.getNowAttendance(user)==null){
            todayState = false;
        }else if ((attendanceService.getNowAttendance(user) != null) & (attendanceService.getNowAttendance(user).getEndWorkTime()== null) ) {
            todayState = true;
            model.addAttribute("startWorkTime",attendanceService.getNowAttendance(user).getStartWorkTime());

        }else if((attendanceService.getNowAttendance(user) != null) & (attendanceService.getNowAttendance(user).getEndWorkTime()!= null)){
            todayState = false;
        }else {
            todayState = false;
        }

        model.addAttribute("todayState",todayState);
        model.addAttribute("user",user);
        return "bgAttendance/user_bg_attendance_create";
    }

    //---------------------- 외근신청내역 --------------------------//
    @GetMapping("/user/bg/enroll/list")
    public String bgEnrollRequest(Model model , Principal principal,@RequestParam(value = "page",defaultValue = "0")int page){
        SiteUser user= this.userService.findUser(principal.getName());

        Page<BgAttendance> bgList=this.bgService.getUserList(user,page);

        boolean todayState ;
        if(attendanceService.getNowAttendance(user)==null){
            todayState = false;
        }else if ((attendanceService.getNowAttendance(user) != null) & (attendanceService.getNowAttendance(user).getEndWorkTime()== null) ) {
            todayState = true;
            model.addAttribute("startWorkTime",attendanceService.getNowAttendance(user).getStartWorkTime());

        }else if((attendanceService.getNowAttendance(user) != null) & (attendanceService.getNowAttendance(user).getEndWorkTime()!= null)){
            todayState = false;
        }else {
            todayState = false;
        }

        model.addAttribute("todayState",todayState);
        model.addAttribute("user",user);
        model.addAttribute("paging",bgList);
        return "bgAttendance/user_bg_attendance_list";
    }

}
