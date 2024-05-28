package jmaster.io;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Project3ITestApplication {

	public static void main(String[] args) {
		SpringApplication.run(Project3ITestApplication.class, args);
	}

	
}
