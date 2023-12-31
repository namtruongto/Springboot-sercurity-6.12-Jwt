package com.truongto.mock.config;

import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.truongto.mock.config.jwt.AuthEntryPointJwt;
import com.truongto.mock.config.jwt.JwtAuthFilter;
import com.truongto.mock.thfw.exceptions.CustomAccessDeniedHandler;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity() // Bật hỗ trợ cho @PreAuthorize và @PostAuthorize
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
@RequiredArgsConstructor
public class SecurityConfig {

	private static final String[] PUBLIC = new String[] {
			"/test/**",
			"/auth/**",
			"/swagger-ui/**",
			"/v3/**"
	};

	private static final String[] ADMIN = new String[] {
			"/user/**"
	};

	private static final String[] USER = new String[] {
			"/book/**"
	};

	private static final String[] STAFF = new String[] {
			"/author/**"
	};

	private final UserDetailsService userDetailsService;

	private final JwtAuthFilter jwtAuthFilter;

	private final CustomAccessDeniedHandler customAccessDeniedHandler;

	private final AuthEntryPointJwt authEntryPointJwt;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
				.csrf(AbstractHttpConfigurer::disable) // Bỏ qua CSRF
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers(PUBLIC).permitAll()
						.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // Cho phép tất cả các request OPTIONS
						.requestMatchers(ADMIN).hasAuthority("ADMIN") // If UserDetails.getAuthorities return [ADMIN, ...]
						.requestMatchers(USER).hasAuthority("USER")
						.requestMatchers(STAFF).hasAuthority("STAFF")
						.anyRequest().authenticated())
				.exceptionHandling(exception -> exception
						.accessDeniedHandler(customAccessDeniedHandler)
						.authenticationEntryPoint(authEntryPointJwt)) // Xử lý khi bị từ chối truy cập
				.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Không sử dụng session
				.authenticationProvider(authenticationProvider())
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	private AuthenticationProvider authenticationProvider() {
		final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Bean
	public AuditorAware<String> auditorProvider() {
		return () -> {
			String userName = SecurityContextHolder.getContext().getAuthentication().getName();
			return userName != null ? Optional.of(userName) : Optional.of("SYSTEM");
		};
	}

}
