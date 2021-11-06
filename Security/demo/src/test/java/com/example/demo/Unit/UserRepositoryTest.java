package com.example.demo.Unit;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void validCreate() {
		
		String email = "foo";
		String password = "bar";
		
		this.userRepository.create(email, password);
		
		List<User> users = this.userRepository.findAll();
		
		Assertions.assertEquals(users.size(), 1);
		
		User user = users.get(0);
		
		Assertions.assertEquals(user.getEmail(), email);
		Assertions.assertEquals(user.getPassword(), password);
	}
	
	@Test
	public void findByExistingEmail() {
		
		String email = "foo";
		String password = "bar";
		
		this.userRepository.create(email, password);
		
		List<User> users = this.userRepository.findByEmail(email);
		
		Assertions.assertEquals(users.size(), 1);
		
		User user = users.get(0);
		
		Assertions.assertEquals(user.getEmail(), email);
	}
	
	@Test
	public void findByMissedEmail() {
		
		String email = "foo";
		
		List<User> users = this.userRepository.findByEmail(email);
		
		Assertions.assertEquals(users.size(), 0);
	}
}