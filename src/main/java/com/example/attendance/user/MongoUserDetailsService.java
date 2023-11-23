package com.example.attendance.user;

import com.example.attendance.entity.SiteUser;
import com.example.attendance.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MongoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // 사용자 정보를 로드하는 메서드
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 사용자 이름으로 데이터베이스에서 사용자 찾기
        Optional<SiteUser> _siteUser = this.userRepository.findByusername(username);

        // 사용자가 없으면 예외 발생
        if (_siteUser.isEmpty()) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }

        // 사용자 정보 얻기
        SiteUser siteUser = _siteUser.get();

        // 권한 목록 생성
        List<GrantedAuthority> authorities = new ArrayList<>();

        // 사용자 역할을 authorities에 추가
        authorities.add(new SimpleGrantedAuthority(siteUser.getRole().getValue()));

        // Spring Security에서 사용하는 UserDetails 객체 생성
        return new User(siteUser.getUsername(), siteUser.getPassword(), authorities);
    }
}
