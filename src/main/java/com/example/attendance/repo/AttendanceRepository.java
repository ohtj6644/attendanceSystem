package com.example.attendance.repo;


import com.example.attendance.entity.Attendance;
import com.example.attendance.entity.SiteUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttendanceRepository extends MongoRepository<Attendance, String> {

    Optional<Attendance> findById(String id);

    Attendance findByIdAndUser(String id , SiteUser user);
}
