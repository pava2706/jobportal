package com.pavan.jobportal.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import com.pavan.jobportal.entity.User;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

	private final String SECRET = "mysecretkeymysecretkeymysecretkey"; // min 32 chars
	private final long EXPIRATION = 1000 * 60 * 60; // 1 hour

	private Key getSignKey() {
		return Keys.hmacShaKeyFor(SECRET.getBytes());
	}

	public String generateToken(User user) {
		return Jwts.builder().setSubject(user.getEmail()) // Main data
				.claim("role", user.getRole().name()).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
				.signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
	}

	public String extractEmail(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateToken(String token, String email) {
		return email.equals(extractEmail(token)) && !isTokenExpired(token);
	}

	private boolean isTokenExpired(String token) {
		Date exp = Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody()
				.getExpiration();

		return exp.before(new Date());
	}
}