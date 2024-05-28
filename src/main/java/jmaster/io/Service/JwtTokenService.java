package jmaster.io.Service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtTokenService {

	@Value("${jwt.secret:123}")
	private String secretKey;
	
	private long validity = 5; // 5 phut
	
	// Tạo token
	public String createToken(String username) {
		// Clams chứa các thông tin cần thiết cho token
		Claims claims = Jwts.claims().setSubject(username);
		Date now = new Date();
		Date exp = new Date(now.getTime() + validity*60*1000);
		
		return Jwts.builder().setClaims(claims)
				.setIssuedAt(now)
				.setExpiration(exp)
				.signWith(SignatureAlgorithm.HS256, secretKey)
				.compact();
	}
	
	// Kiểm tra tính hợp lệ của một token JWT đã cho
	public boolean isValidToken( String token) {
		try {
			//Jwts.parser(): Tạo trình phân tích
			// setSigningKey(secretKey): đặt khóa bí mật đã được cấu hình vào trình phân tích
			// parseClaimsJws(token): cố gắng giải mã và kiểm tra token
		Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
		return true;
		}
		catch(Exception e) {
			
		}
		return false;
	}
	
	public String getUsername(String token) {
		try {
			return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
		} catch (Exception e) {
			// do nothing
		}
		return null;
	}
}
