package com.pavan.jobportal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
	
	
	@Autowired
	
	private JwtFilter jwtFilter;

	// ✅ Password Encoder Bean
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// ✅ Allow APIs (VERY IMPORTANT)
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable())
		.authorizeHttpRequests(auth -> auth
			    .requestMatchers(
			        "/api/users/register/jobseeker",
			        "/api/users/register/recruiter",
			        "/api/users/login"
			    ).permitAll()

			    // ✅ Role-based access
			    .requestMatchers("/api/recruiter/**").hasRole("RECRUITER")
			    .requestMatchers("/api/jobseeker/**").hasRole("JOBSEEKER")

			    .anyRequest().authenticated()
			)
		.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
}