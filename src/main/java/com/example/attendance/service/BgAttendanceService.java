package com.example.attendance.service;


import com.example.attendance.repo.BgAttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BgAttendanceService {

    private final BgAttendanceRepository BgRepo;
}
