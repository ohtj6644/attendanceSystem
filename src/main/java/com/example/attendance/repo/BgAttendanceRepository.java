package com.example.attendance.repo;


import com.example.attendance.entity.Annual;
import com.example.attendance.entity.BgAttendance;
import com.example.attendance.entity.SiteUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BgAttendanceRepository extends MongoRepository<BgAttendance,String> {

    Page<BgAttendance> findByUser(Pageable pageable, SiteUser user);

    Page<BgAttendance> findAll(Pageable pageable);
}
