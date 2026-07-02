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

		String path = request.getServletPath();

		// Skip AI APIs
		if(path.startsWith("/ai")
				   || path.startsWith("/swagger-ui")
				   || path.startsWith("/v3"))
				{
				    filterChain.doFilter(
				       request,response);

				    return;
				}

		String authHeader = request.getHeader("Authorization");
		String token = null;
		String email = null;

		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			token = authHeader.substring(7);
		}

		if (token != null) {

			try {

				email = jwtUtil.extractEmail(token);
				String role = jwtUtil.extractRole(token);

				if (email != null && SecurityContextHolder.getContext().getAuthentication() == null
						&& jwtUtil.validateToken(token, email)) {

					List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + role));

					UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(email, null,
							authorities);

					SecurityContextHolder.getContext().setAuthentication(auth);
				}

			} catch (Exception e) {

				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

				return;
			}
		}

		filterChain.doFilter(request, response);
	}
}
