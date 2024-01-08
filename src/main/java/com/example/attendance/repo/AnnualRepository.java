package com.example.attendance.repo;


import com.example.attendance.entity.Annual;
import com.example.attendance.entity.SiteUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnualRepository extends MongoRepository<Annual,String> {

    Page<Annual> findAllAndUser(Pageable pageable, SiteUser user);
}
