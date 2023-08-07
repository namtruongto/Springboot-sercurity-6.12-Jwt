package com.truongto.mock.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.truongto.mock.entities.Person;
import com.truongto.mock.thfw.enums.Role;

@Service
public class DatabaseService {
    @Autowired
	private PersonService personService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void initializeDatabase() {

		System.out.println("Initializing database...");
		
		final Person user1 = new Person("truong", "emma@mail.com", passwordEncoder.encode("111"));
		final Person user2 = new Person("huong", "jhon@mail.com", passwordEncoder.encode("222"));
		final Person admin = new Person("admin", "anna@mail.com", passwordEncoder.encode("333"));
		
		admin.addRole(Role.ADMIN);
		admin.addRole(Role.USER);
	
		System.out.println(personService.create(user1));
		System.out.println(personService.create(user2));
		System.out.println(personService.create(admin));
		
		System.out.println("Database initialized!");
	}
}
