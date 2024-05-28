package jmaster.io;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jmaster.io.Service.JwtTokenService;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {

	@Autowired
	// Dùng để đọc token
	JwtTokenService jwtTokenService;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Override
	//tải thông tin người dùng từ cơ sở dữ liệu, tạo đối tượng xác thực và đặt nó trong SecurityContextHolder để xác thực người dùng 
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		// doc header authorize
		String bearerToken = request.getHeader("Authorization");
		log.info(bearerToken);
		
		if(bearerToken != null && bearerToken.startsWith("Bearer ")) {
			// bo chu Bearer
			String token = bearerToken.substring(7);
			String username = jwtTokenService.getUsername(token);
			if(username != null) {
				// token valid, create a authentication
				//Dòng này sử dụng userDetailsService để tải thông tin người dùng dựa trên tên người dùng đã trích xuất từ mã thông báo JWT. 
				//Kết quả là đối tượng userDetails chứa thông tin người dùng bao gồm tên người dùng, mật khẩu và danh sách các quyền (authorities).
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				
				
				//userDetails: user đăng nhập, password, list quyền
				//Dòng mã tạo một đối tượng xác thực (Authentication) được gọi là authenticationư
				//. Đối tượng này được tạo bằng cách sử dụng lớp UsernamePasswordAuthenticationToken 
				Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, "",userDetails.getAuthorities());
				
				// gia lap security
				//: Dòng mã này đặt đối tượng authentication vào SecurityContextHolder để xác thực người dùng
				// SecurityContextHolder là một lớp trong Spring Security dùng để lưu trữ và truy cập đối tượng xác thực hiện tại
				// Bằng cách đặt đối tượng authentication vào SecurityContextHolder, chúng ta đang xác nhận rằng người dùng đã được xác thực 
				//và có quyền thực hiện các hoạt động yêu cầu xác thực trong ứng dụng.
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		
		// cho request đi
		filterChain.doFilter(request, response);
	}
}
