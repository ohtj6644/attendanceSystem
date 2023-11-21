package com.example.attendance.repo;

import com.example.attendance.entity.Uuid;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UuidRepository extends MongoRepository<Uuid,String> {

    Uuid findTopByOrderByIdDesc();
}
