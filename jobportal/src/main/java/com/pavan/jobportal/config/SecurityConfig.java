package com.pavan.jobportal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

	@Autowired
	private JwtFilter jwtFilter;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable())

				// Disable default spring login page
				.formLogin(form -> form.disable())

				// Disable browser username/password popup
				.httpBasic(httpBasic -> httpBasic.disable())
				.sessionManagement(session ->
				    session.sessionCreationPolicy(
				        SessionCreationPolicy.STATELESS))

				.authorizeHttpRequests(auth -> auth

						// Public APIs
						.requestMatchers("/api/users/register/jobseeker", 
								         "/api/users/register/recruiter",
								         "/api/users/login", 
								         "/ai/**",
								         "/api/jobs/**",
								         "/swagger-ui/**",
								         "/v3/api-docs/**")
						.permitAll()

						// Role-based APIs
						.requestMatchers("/api/recruiter/**").hasRole("RECRUITER").requestMatchers("/api/jobseeker/**")
						.hasRole("JOBSEEKER")

						// Everything else secured
						.anyRequest().authenticated())

				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
}