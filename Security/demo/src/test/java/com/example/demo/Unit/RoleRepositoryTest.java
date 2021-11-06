package com.example.demo.Unit;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.constant.RoleName;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class RoleRepositoryTest {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Test
	public void findByNameSuccess() {
		
		String name = RoleName.CLIENT.get();
		
		List<Role> roles = this.roleRepository.findByName(name);
		
		Assertions.assertFalse(roles.isEmpty());
		Assertions.assertEquals(roles.size(), 1);
		Assertions.assertEquals(roles.get(0).getName(), name);
	}
	
	@Test
	public void findByNameFailed() {
		
		String name = RoleName.ADMIN.get();
		
		List<Role> roles = this.roleRepository.findByName(name);
		
		Assertions.assertTrue(roles.isEmpty());
	}
}