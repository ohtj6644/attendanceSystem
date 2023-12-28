package com.example.attendance.service;


import com.example.attendance.entity.Uuid;
import com.example.attendance.repo.UserRepository;
import com.example.attendance.repo.UuidRepository;
import com.example.attendance.entity.SiteUser;
import com.example.attendance.user.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final UuidRepository uuidRepo;



    //------------------------유저 생성 -----------------------//

    public SiteUser newUser(String username, String password, String realName , LocalDateTime singupDate) {
        SiteUser user = new SiteUser();
        user.setUsername(username);
        if(username.equals("admin")){
            user.setRole(UserRole.ADMIN);
        }
        // 생성된 user 이름이 "admin" 일 경우 관리자 권한 부여 //


        user.setPassword(passwordEncoder.encode(password));
        user.setSignupDate(singupDate);
        user.setRealName(realName);

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



    //----------------------------user login--------------------------------------//
    public boolean authenticateUser(String username, String password) {
        Optional<SiteUser> siteUserOptional = this.userRepo.findByusername(username);
        if (siteUserOptional.isPresent()) {
            SiteUser siteUser = siteUserOptional.get();
            return passwordEncoder.matches(password, siteUser.getPassword());
        }
        return false;
    }

    //----------------------------모든유저 반환-------------------------------------//
    public List<SiteUser> getAllUsers(){
        return this.userRepo.findAll();
    }
    //-----------------------------유저 저장 ----------------------------------------//
    public void saveUser(SiteUser user){
        this.userRepo.save(user);
    }


    public Page<SiteUser> getList(int page){
        List<Sort.Order> sorts=new ArrayList<>();
        sorts.add(Sort.Order.desc("uuid"));
        Pageable pageable = PageRequest.of(page,15,Sort.by(sorts));
        return this.userRepo.findAll(pageable);
    }


    //---------------------------관리자권한 부여 --------------------------------//
    public void grantUp(String id){
        Optional<SiteUser> siteUserOptional = this.userRepo.findById(id);
        SiteUser siteUser = siteUserOptional.get();
        siteUser.setRole(UserRole.ADMIN);
        this.userRepo.save(siteUser);
    }


    //---------------------------관리자권한 회수 --------------------------------//
    public void grantDown(String id){
        Optional<SiteUser> siteUserOptional = this.userRepo.findById(id);
        SiteUser siteUser = siteUserOptional.get();
        siteUser.setRole(UserRole.USER);
        this.userRepo.save(siteUser);
    }

}
