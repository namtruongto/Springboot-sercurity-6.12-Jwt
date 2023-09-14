package com.truongto.mock.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.truongto.mock.entities.User;
import com.truongto.mock.thfw.enums.Role;

@Service
@RequiredArgsConstructor
public class DatabaseService {
	private final UserService userService;

	private final PasswordEncoder passwordEncoder;
	
	public void initializeDatabase() {

		System.out.println("Initializing database...");
		
		final User user1 = new User("truong", "emma@mail.com", passwordEncoder.encode("123456"));
		final User user2 = new User("huong", "jhon@mail.com", passwordEncoder.encode("123456"));
		final User admin = new User("ad", "anna@mail.com", passwordEncoder.encode("123456"));
		
		admin.addRole(Role.ADMIN);
		admin.addRole(Role.USER);
	
		System.out.println(userService.create(user1));
		System.out.println(userService.create(user2));
		System.out.println(userService.create(admin));
		
		System.out.println("Database initialized!");
	}
}
