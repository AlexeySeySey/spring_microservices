package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.constant.Error;
import com.example.demo.constant.RoleName;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.exception.UserNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@PersistenceContext
    private EntityManager em;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public void registerUser(String email, String password) throws Exception {
		
		List<Role> roles = this.roleRepository.findByName(RoleName.CLIENT.get());
		
		if (roles.size() == 0) {
			throw new Exception(Error.ROLE_ASSIGNMENT.get());
		}
		Role role = roles.get(0);
		
		password = this.bCryptPasswordEncoder.encode(password);
		
		this.userRepository.create(email, password);
		
		User userEntity = this.userRepository.findByEmail(email).get(0);
		
		userEntity.setRole(role);
		this.em.persist(userEntity);
		this.em.flush();
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public User findByEmail(String email) throws Exception {
		
		List<User> users = this.userRepository.findByEmail(email);
		
		if (users.size() == 0) {
			throw new UserNotFoundException(Error.USER_NOT_FOUND.get());
		}
		
		return users.get(0);
	}
	
	public boolean passwordsSame(User user, String password) {
		return this.bCryptPasswordEncoder.matches(password, user.getPassword());
	}
}