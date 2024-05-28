package jmaster.io;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration // được sử dụng để đánh dấu lớp là một cấu hình và cho phép bạn tạo và cấu hình các bean trong ứng dụng Spring Boot.
@EnableWebSecurity
public class SecutityConfig  {

	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	JwtTokenFilter tokenFilter;
	
	// Xác thực(Đăng nhập username, password)
	@Autowired
	public void config( AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	//AuthenticationManager là một interface, nó chịu trách nhiệm xác thực thông tin đăng nhập (username và password) 
	//và quyết định liệu người dùng có được phép truy cập hay không.
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	// Kiểm tra đường dẫn
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.authorizeHttpRequests(
				requests -> requests.requestMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_SUBADMIN")
				.requestMatchers("/customer/**").authenticated()
				.anyRequest().permitAll())
				// yêu cầu người dùng cung cấp tên người dùng và mật khẩu khi truy cập.
				// Customizer.withDefaults() được sử dụng để cấu hình các giá trị mặc định cho xác thực HTTP Basic.
				.httpBasic(Customizer.withDefaults()).csrf().disable().
				addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class).build();
		//cau hinh thieu ma
		// Apply JWT
				
	}
}
