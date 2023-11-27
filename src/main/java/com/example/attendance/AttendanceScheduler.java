package com.example.attendance;


import com.example.attendance.entity.Attendance;
import com.example.attendance.entity.SiteUser;
import com.example.attendance.service.AttendanceService;
import com.example.attendance.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AttendanceScheduler {

    private final AttendanceService attendanceService;

    //---------------------------퇴근처리 없는 근무 삭제--------------------//
    // 매월 자정에 실행
    @Scheduled(cron = "0 0 0 * * ?")
    public void NotTheEndAttendanceDelete(){

        List<Attendance> attendances = attendanceService.getYesterdayAttendance();
        // 오늘 생성된 근무 전체를 리스트로 받아옴.

        for (Attendance attendance : attendances) {

            if(attendance.getEndWorkTime()==null){
                this.attendanceService.deleteAttendance(attendance);
            }
            // 해당 근무에 퇴근시간이 없을 경우 (퇴근처리를 안했을 경우) 해당근무 삭제

        }

    }


}
