package com.truongto.mock.config.jwt;

import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtService {
    
    @Value("${jwt.secret.key}")
	private String secretKey;
	
	@Value("${jwt.expiration}")
	private Long expiration;

    // Hàm getKey() được sử dụng để tạo ra một khóa bí mật (SecretKey) từ chuỗi ký tự secretKey đã cho. 
    // Hàm này sử dụng thuật toán HMAC SHA để tạo ra khóa bí mật. 
    // Đầu vào: Không có. 
    // Đầu ra: Một đối tượng SecretKey được tạo ra từ chuỗi ký tự secretKey đã cho. 
    private SecretKey getKey() {
		return Keys.hmacShaKeyFor(secretKey.getBytes());
	}

    public String generateToken(String username) {
		return Jwts.builder() //.builder() để tạo ra một đối tượng JwtBuilder.
			.setSubject(username) //.setSubject() để thiết lập thông tin người dùng cho token.
			.setIssuedAt(new Date(System.currentTimeMillis())) //.setIssuedAt() để thiết lập thời gian phát hành token.
			.setExpiration(new Date(System.currentTimeMillis() + expiration)) //.setExpiration() để thiết lập thời gian hết hạn của token.
			.signWith(getKey(), SignatureAlgorithm.HS512) // .signWith() để thiết lập thuật toán mã hóa và khóa bí mật.
			.compact(); //.compact() để tạo ra chuỗi token.
	}
	
	public Claims getClaims(String token) { // giải mã token để lấy thông tin người dùng
		return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody();
	}

	public String getUsername(String token) {
		return getClaims(token).getSubject();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token);
			return true;
		}
		catch(SignatureException e){
			log.error("Invalid JWT signature: {}", e.getMessage());
			e.printStackTrace();
			
		}
		catch (SecurityException | MalformedJwtException e) {
			log.error("Invalid JWT token: {}", e.getMessage());
			e.printStackTrace();
		}
		catch (ExpiredJwtException e) {
			log.error("JWT token is expired: {}", e.getMessage());
			e.printStackTrace();
		}
		catch (UnsupportedJwtException e) {
			log.error("JWT token is unsupported: {}", e.getMessage());
			e.printStackTrace();
		}
		catch (IllegalArgumentException e) {
			log.error("JWT claims string is empty: {}", e.getMessage());
			e.printStackTrace();
		}
		return false;
	}
}
