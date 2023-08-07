package com.truongto.mock.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
// import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.truongto.mock.thfw.exceptions.CustomAccessDeniedHandler;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true) // Bật hỗ trợ cho @PreAuthorize và @PostAuthorize
public class SecurityConfig {

    private static final String[] PUBLIC = new String[] {
			"/test/**",
			"/auth/**"
		};
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtAuthFilter jwtAuthFilter;

	@Autowired
	private CustomAccessDeniedHandler customAccessDeniedHandler;

	@Autowired	
	private AuthEntryPointJwt authEntryPointJwt;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
                .csrf(csrf -> csrf.disable()) // Bỏ qua CSRF
                .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(PUBLIC).permitAll()
				.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // Cho phép tất cả các request OPTIONS
                //.requestMatchers(HttpMethod.DELETE).hasAuthority("ADMIN") 	// If UserDetails.getAuthorities return [ADMIN, ...]
                //.requestMatchers("/person/**").hasAuthority("USER")			// If UserDetails.getAuthorities return [USER, ...]
                .anyRequest().authenticated())
				.exceptionHandling(exception -> exception
					.accessDeniedHandler(customAccessDeniedHandler)
					.authenticationEntryPoint(authEntryPointJwt)
									) // Xử lý khi bị từ chối truy cập
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
		
	// @Bean
	// public WebSecurityCustomizer webSecurityCustomizer() {
	// 	return (web) -> web.ignoring().requestMatchers(new AntPathRequestMatcher("/api/auth/**"));
	// }

	private AuthenticationProvider authenticationProvider() {
		final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

}
