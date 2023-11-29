package com.example.attendance;


import com.example.attendance.entity.SiteUser;
import com.example.attendance.repo.UserRepository;
import com.example.attendance.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;



@EnableFeignClients
@SpringBootApplication
@ComponentScan(basePackages = "com.example")
@RequiredArgsConstructor
public class AttendanceApplication implements CommandLineRunner {

	private final UserService userService;
	private final UserRepository userRepository;


	public static void main(String[] args) {
		SpringApplication.run(AttendanceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if(userRepository.findByusername("admin")==null){
			this.userService.newUser("admin","1234");
		}

	}




}
