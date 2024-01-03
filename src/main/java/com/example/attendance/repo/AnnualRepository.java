package com.example.attendance.repo;


import com.example.attendance.entity.Annual;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnualRepository extends MongoRepository<Annual,String> {
}
