package com.truongto.mock.config.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter{

    @Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
      if (SecurityContextHolder.getContext().getAuthentication() == null) {
		
			final String authorization = request.getHeader("Authorization");
			if (authorization != null && authorization.startsWith("Bearer ")) {
		
				final String token = authorization.substring(7);
				if (jwtService.validateToken(token)) {
					final Claims claims = jwtService.getClaims(token);
					final String username = claims.getSubject();
					final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
					
					final UsernamePasswordAuthenticationToken authToken =
							new UsernamePasswordAuthenticationToken(
									userDetails, null, userDetails.getAuthorities());
													
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authToken);
				}
			}
		}
		//Bổ sung cấu hình cho phép header và chấp nhận CORS policy ở đây
		response.setHeader("Access-Control-Allow-Origin", "*"); // *: cho phép tất cả các domain khác gọi vào
		response.setHeader("Access-Control-Allow-Methods", "GET"); // Cho phép các phương thức
		response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept, X-Requested-With, remember-me"); // Cho phép dữ liệu gửi lên server có dạng json, form data
		response.setHeader("Access-Control-Max-Age", "3600"); // Thời gian cho phép truy cập
		response.setHeader("Access-Control-Allow-Credentials", "true"); // Cho phép gửi cookie
		filterChain.doFilter(request, response); // Cho phép đi tiếp
    }
}
