package com.pavan.jobportal.config;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

	private final JwtUtil jwtUtil;

	public JwtFilter(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String authHeader = request.getHeader("Authorization");
		String token = null;
		String email = null;

		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			token = authHeader.substring(7);
		}

		if (token != null) {

			email = jwtUtil.extractEmail(token);

			String role = jwtUtil.extractRole(token);
			

			if (jwtUtil.validateToken(token, email)) {

				List<SimpleGrantedAuthority> authorties = List.of(new SimpleGrantedAuthority("ROLE_" + role));

				// 🔥 Create Authentication object
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(email, null,
						authorties);

				// 🔐 Set authentication
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}

		// ✅ Continue request
		filterChain.doFilter(request, response);

	}

}
