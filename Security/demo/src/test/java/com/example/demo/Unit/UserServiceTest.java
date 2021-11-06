package com.example.demo.Unit;

import java.awt.List;
import java.util.ArrayList;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockingDetails;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.constant.Error;
import com.example.demo.constant.RoleName;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.mockito.internal.util.MockUtil;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@Mock
	private UserRepository userRepository;
	
	@Mock
	private RoleRepository roleRepository;

	@Mock
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Mock
	private EntityManager em;

	@InjectMocks
	private UserService userService;

	@Test
	public void findByEmailSuccess() {

		var wrapper = new Object() {
			User user = null;
			String emailValue = "foo@foo.foo";
		};

		User user = new User();
		user.setId(Long.valueOf(1));
		user.setEmail(wrapper.emailValue);
		user.setPassword("bar");

		ArrayList<User> users = new ArrayList<User>();
		users.add(user);

		Mockito.when(userRepository.findByEmail(wrapper.emailValue)).thenReturn(users);

		Assertions.assertDoesNotThrow(() -> {
			wrapper.user = userService.findByEmail(wrapper.emailValue);
		});

		Assertions.assertNotNull(wrapper.user);
		Assertions.assertEquals(wrapper.user.getEmail(), wrapper.emailValue);
	}

	@Test
	public void findByEmailFailed() {

		var wrapper = new Object() {
			User user = null;
			String emailValue = "foo";
		};

		Mockito.when(userRepository.findByEmail(wrapper.emailValue)).thenReturn(new ArrayList<User>());

		Assertions.assertThrows(UserNotFoundException.class, () -> {
			wrapper.user = userService.findByEmail(wrapper.emailValue);
		});

		Assertions.assertNull(wrapper.user);
	}

	@Test
	public void passwordsSame() {

		String password = "foobarbaz";

		String encodedPassword = new BCryptPasswordEncoder().encode(password);

		Mockito.when(bCryptPasswordEncoder.matches(password, encodedPassword)).thenReturn(true);

		User user = new User();
		user.setId(Long.valueOf(1));
		user.setEmail("foo");
		user.setPassword(encodedPassword);

		boolean passwordsSame = userService.passwordsSame(user, password);

		Assertions.assertTrue(passwordsSame);
	}

	@Test
	public void passwordsDifferent() {

		String password = "foobarbaz";

		String encodedPassword = new BCryptPasswordEncoder().encode("test");

		Mockito.when(bCryptPasswordEncoder.matches(password, encodedPassword)).thenReturn(false);

		User user = new User();
		user.setId(Long.valueOf(1));
		user.setEmail("foo");
		user.setPassword(encodedPassword);

		boolean passwordsSame = userService.passwordsSame(user, password);

		Assertions.assertFalse(passwordsSame);
	}
	
	@Test
	public void registrationFailedRoleAssignment() {
		
		Mockito.when(roleRepository.findByName(RoleName.CLIENT.get())).thenReturn(new ArrayList<Role>());
		
		Exception exception = Assertions.assertThrows(Exception.class, () -> {
			userService.registerUser("", "");
		});
		
		Assertions.assertEquals(exception.getMessage(), Error.ROLE_ASSIGNMENT.get());
	}
	
	@Test
	public void reigstrationSuccess() {
		
		ArrayList<Role> roles = new ArrayList<Role>();
		
		Role role = new Role();
		role.setName(RoleName.CLIENT.get());
		
		roles.add(role);
		
		Mockito.when(roleRepository.findByName(RoleName.CLIENT.get())).thenReturn(roles);
		
		var wrapper = new Object() {
			String email = "foo@foo.foo";
			String password = "foobarbaz";
		};
		
		String encodedPassword = new BCryptPasswordEncoder().encode(wrapper.password);
		
		Mockito.when(bCryptPasswordEncoder.encode(wrapper.password)).thenReturn(encodedPassword);
		
		User user = new User();
		user.setId(Long.valueOf(1));
		user.setEmail(wrapper.email);
		user.setPassword(wrapper.password);
		
		ArrayList<User> users = new ArrayList<User>();
		users.add(user);
		
		Mockito.when(userRepository.findByEmail(wrapper.email)).thenReturn(users);
		
		Assertions.assertDoesNotThrow(() -> {
			userService.registerUser(wrapper.email, wrapper.password);
		});
	}
}