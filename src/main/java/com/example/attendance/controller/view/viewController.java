package com.example.attendance.controller.view;


import com.example.attendance.entity.Attendance;
import com.example.attendance.entity.SiteUser;
import com.example.attendance.service.AttendanceService;
import com.example.attendance.service.UserService;
import com.example.attendance.user.UserCreateForm;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

    //----------------유저 회원가입 화면 반환 ------------------//
    @GetMapping("/signup")
    public String signUp(UserCreateForm userCreateForm){

        return "sign_up";

    }

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
    public String mainPage(Model model){


        return "main_Page";
    }



    //---------------------- 한달 간 근무 내역 --------------------------//
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/user/monthAttendance/")
    public String getMonthAttendance(Model model, Principal principal, @RequestParam(value = "month",defaultValue = "0") int month,
                                     @RequestParam(value = "year",defaultValue = "0") int year){
        SiteUser user= this.userService.findUser(principal.getName());
        List<Attendance> attendances = attendanceService.getMonthAttendance(user , month , year);
        int attendanceSize= attendances.size();

        model.addAttribute("attendances",attendances);
        //한달동안 근무한 내역 리스트로 반환
        model.addAttribute("attendanceSize",attendanceSize);
        //한달동안 근무한 날짜 수 반환

        return "Month_Attendance";
    }

}
