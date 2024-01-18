package com.example.attendance.controller.api.bgAttendance;


import com.example.attendance.service.BgAttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BgAttendanceController {

    private final BgAttendanceService Bgservice;
}
