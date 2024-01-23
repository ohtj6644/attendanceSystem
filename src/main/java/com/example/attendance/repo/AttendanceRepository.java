package com.example.attendance.repo;


import com.example.attendance.entity.Attendance;
import com.example.attendance.entity.SiteUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends MongoRepository<Attendance, String> {

    Optional<Attendance> findById(String id);

    Attendance findByIdAndUser(String id , SiteUser user);

    List<Attendance> findByUserAndStartWorkTimeBetween(SiteUser user, LocalDateTime start, LocalDateTime end);

    List<Attendance> findByStartWorkTimeBetween(LocalDateTime start, LocalDateTime end);

    Page<Attendance> findByStartWorkTimeBetweenAndUser(LocalDateTime startDate, LocalDateTime endDate, SiteUser user, Pageable pageable);
}
