package com.example.attendance.service;


import com.example.attendance.entity.Uuid;
import com.example.attendance.repo.UserRepository;
import com.example.attendance.repo.UuidRepository;
import com.example.attendance.entity.SiteUser;
import com.example.attendance.user.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final UuidRepository uuidRepo;



    //------------------------유저 생성 -----------------------//

    public SiteUser newUser(String username, String password) {
        SiteUser user = new SiteUser();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setSignupDate(LocalDateTime.now());

        Uuid uuid = new Uuid();

        Uuid latestUuid = this.uuidRepo.findTopByOrderByIdDesc();
        if (latestUuid == null) {
            uuid.setId("00000000");
        } else {
            int nextId = Integer.parseInt(latestUuid.getId()) + 1;
            uuid.setId(String.format("%08d", nextId));
        }

        user.setUuid_id(uuid);
        user.setUuid(uuid.getId());
        this.userRepo.insert(user);
        uuid.setUser(user);
        this.uuidRepo.insert(uuid);

        return user;
    }


    //--------------------username 으로 user를 찾아서 반환 ------------------------//

    public SiteUser findUser(String username){
        return this.userRepo.findByUsername(username);
    }

}
