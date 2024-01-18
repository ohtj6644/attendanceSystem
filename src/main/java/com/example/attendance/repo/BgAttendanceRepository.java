package com.example.attendance.repo;


import com.example.attendance.entity.BgAttendance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BgAttendanceRepository extends MongoRepository<BgAttendance,String> {
}
