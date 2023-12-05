package com.example.attendance.repo;

import com.example.attendance.entity.Notice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NoticeRepository  extends MongoRepository<Notice,String> {
}
