package jmaster.io;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;
import jmaster.io.Repository.RoleRepo;
import jmaster.io.Repository.UserRepo;
import jmaster.io.entity.Role;
import jmaster.io.entity.User;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
// Khi khởi động lên mình sẽ insert dữ liệu, đỡ mất công gọi api
public class DemoData implements ApplicationRunner {

	// Hệ thống lần đầu bao giờ cũng phải có role admin và system admin
	@Autowired
	RoleRepo roleRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@Override
	//@Transactional
	public void run(ApplicationArguments args) throws Exception {
		log.info("BEGIN INSET");
		// insert data demo into data
		 Role role= new Role();
		// role.setId(2);
		 role.setName("ROLE_ADMIN");
		if(roleRepo.findByName(role.getName()) == null) {
			try {
			roleRepo.save(role);
			log.info("INSERT DUMP");
			User user = new User();
			user.setUsername("sysadmin");
			user.setPassword(new BCryptPasswordEncoder().encode("123456"));
			user.setName("SYS ADMIN");
			user.setEmail("admin@gmail.com");
			user.setBirthdate(new Date());
			user.setRoles(Arrays.asList(role));
			
			userRepo.save(user);
			}catch(Exception e){
				
			}
		}
		
	}

}
