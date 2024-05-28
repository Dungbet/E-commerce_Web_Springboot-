package jmaster.io.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import jmaster.io.DTO.ResponseDTO;
import jmaster.io.DTO.UserDTO;
import jmaster.io.Service.JwtTokenService;
import jmaster.io.Service.UserService;

@RestController
public class LoginAPI {

	
	// Sử dụng để xác thực người dùng
	@Autowired
	AuthenticationManager authenticationManager;
	
	
	@Autowired
	JwtTokenService jwtTokenService;
	
	@Autowired
	UserService userService;
	
	// Lấy thông tin của thằng người dùng đang đăng nhập
	@GetMapping("/me")
	@PreAuthorize("isAuthenticated()")
	public UserDTO me(Principal p) {
		String username =  p.getName();
		UserDTO user = userService.findByUsername(username);
		return user;
	}
	@PostMapping("/login")
	public ResponseDTO<String> login(HttpSession session,
			@RequestParam("username") String username,
			@RequestParam("password") String password
			){
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		
		return ResponseDTO.<String>builder().status(200)
				.data(jwtTokenService.createToken(username))
				.build();
		
	}
	
}
