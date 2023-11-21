package com.example.attendance.service;


import com.example.attendance.repo.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private  final AttendanceRepository attenRepo;
}
