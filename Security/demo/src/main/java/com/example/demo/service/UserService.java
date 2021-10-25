package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.constant.Error;
import com.example.demo.constant.RoleName;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@PersistenceContext
    private EntityManager em;
	
	public boolean isUniqueByEmail(String email) {	
		return this.userRepository
				.findByEmail(email)
				.size() == 0;
	}
	
	@Transactional
	public void registerUser(String email, String password) throws Exception {
		
		Role role = (Role) this.em
				.createQuery("select r from Role r where r.name = :role", Role.class)
				.setParameter("role", RoleName.CLIENT.get())
				.getSingleResult();
		
		if (role == null) {
			throw new Exception(Error.ROLE_ASSIGNMENT.get());
		}
		
		this.userRepository.create(email, password);
		
		User userEntity = this.userRepository.findByEmail(email).get(0);
		
		userEntity.setRole(role);
		this.em.persist(userEntity);
		this.em.flush();
	}
	
	public boolean isRegistered(String email, String password) {
		User user = null;
		try {
			user = this.userRepository.findByEmail(email).get(0);
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
		return this.bCryptPasswordEncoder.matches(password, user.getPassword());
	}
	
	public User findByEmail(String email) throws Exception {
		
		List<User> users = this.userRepository.findByEmail(email);
		
		if (users.size() == 0) {
			throw new Exception(Error.USER_NOT_FOUND.get());
		}
		
		return users.get(0);
	}
}