package com.example.attendance.repo;


import com.example.attendance.entity.CompanyFile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyFileRepository extends MongoRepository<CompanyFile, String> {
}
