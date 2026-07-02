package com.pavan.jobportal.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.pavan.jobportal.entity.User;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

	@Value("${jwt.secret}")
	private String SECRET;

	@Value("${jwt.expiration}")
	private long EXPIRATION;

	// 🔑 Generate signing key
	private Key getSignKey() {
		return Keys.hmacShaKeyFor(SECRET.getBytes());
	}

	// 🔐 Generate JWT Token
	public String generateToken(User user) {
		return Jwts.builder().setSubject(user.getEmail()) // main identity
				.claim("role", user.getRole().name()) // custom claim
				.setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
				.signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
	}

	// 📧 Extract Email (Subject)
	public String extractEmail(String token) {
		return getClaims(token).getSubject();
	}

	// 🎭 Extract Role (NEW)
	public String extractRole(String token) {
	    return getClaims(token)
	            .get("role", String.class);
	}
	
	// 🔍 Validate Token (UPDATED with try-catch)
	public boolean validateToken(String token, String email) {
		try {
			String extractedEmail = extractEmail(token);
			return extractedEmail.equals(email) && !isTokenExpired(token);
		} catch (JwtException | IllegalArgumentException e) {
			return false;
		}
	}

	// ⏳ Check Expiry
	private boolean isTokenExpired(String token) {
		return getClaims(token).getExpiration().before(new Date());
	}

	// 📦 Common method to get claims (clean code)
	private Claims getClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
	}
}