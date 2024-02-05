package com.example.attendance.repo;

import com.example.attendance.entity.SiteUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends MongoRepository<SiteUser,String> {

    Optional<SiteUser> findByusername(String username);

    SiteUser findByUsername(String username);
    List<SiteUser>  findByRealNameLike(String realName);
}
