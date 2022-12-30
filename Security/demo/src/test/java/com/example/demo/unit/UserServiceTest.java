package com.example.demo.unit;

import static org.mockito.ArgumentMatchers.any;

import com.example.demo.constant.Error;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import java.util.Optional;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

    String emailValue = "foo@foo.foo";

    User user = new User();
    user.setId(1L);
    user.setEmail(emailValue);
    user.setPassword("bar");

    Mockito.when(userRepository.findByEmail(emailValue)).thenReturn(Optional.of(user));

    UserDto userDto = userService.findByEmail(emailValue).orElse(null);

    Assertions.assertNotNull(userDto);
    Assertions.assertEquals(userDto.getEmail(), emailValue);
  }

  @Test
  public void findByEmailFailed() {

    String emailValue = "foo@foo.foo";

    Mockito.when(userRepository.findByEmail(emailValue)).thenReturn(Optional.empty());

    Optional<UserDto> user = userService.findByEmail(emailValue);

    Assertions.assertTrue(user.isEmpty());
  }

  @Test
  public void passwordsSame() {

    String password = "foo";
    String encodedPassword = "bar";

    Mockito.when(bCryptPasswordEncoder.matches(password, encodedPassword)).thenReturn(true);

    Assertions.assertTrue(userService.passwordsSame(password, encodedPassword));
  }

  @Test
  public void passwordsDifferent() {

    String password = "foo";
    String encodedPassword = "bar";

    Mockito.when(bCryptPasswordEncoder.matches(password, encodedPassword)).thenReturn(false);

    Assertions.assertFalse(userService.passwordsSame(password, encodedPassword));
  }

  @Test
  public void registrationSuccess() {

    String email = "foo@bar.baz";
    String password = "secret";

    Role role = new Role();
    role.setId(1L);
    role.setName("client");

    Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.empty());
    Mockito.when(roleRepository.findByName("client")).thenReturn(Optional.of(role));
    Mockito.when(userRepository.save(any(User.class))).thenAnswer((i) -> i.getArguments()[0]);

    var userSaved = new Object() {
      User user = null;
    };
    Assertions.assertDoesNotThrow(() -> userSaved.user = userService.registerUser(email, password));
    Assertions.assertEquals(email, userSaved.user.getEmail());
    Assertions.assertEquals(role, userSaved.user.getRole());
    Assertions.assertTrue(userSaved.user.getRole().getPermissions().isEmpty());
  }

  @Test
  public void registrationFailedUserAlreadyRegistered() {
    String email = "foo@bar.baz";
    String password = "secret";

    User user = new User();
    user.setEmail(email);
    user.setPassword(password);

    Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

    Exception exception = Assertions.assertThrows(Exception.class, () -> {
      userService.registerUser(email, password);
    });

    Assertions.assertEquals(exception.getMessage(), Error.USER_REGISTERED.get());

    Mockito.verify(roleRepository, Mockito.never()).findByName("client");
    Mockito.verify(bCryptPasswordEncoder, Mockito.never()).encode(password);
    Mockito.verify(userRepository, Mockito.never()).save(any(User.class));
  }

  @Test
  public void registrationFailedRoleNotFound() {
    String email = "foo@bar.baz";
    String password = "secret";

    Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.empty());
    Mockito.when(roleRepository.findByName("client")).thenReturn(Optional.empty());

    Exception exception = Assertions.assertThrows(Exception.class, () -> {
      userService.registerUser(email, password);
    });

    Assertions.assertEquals(exception.getMessage(), Error.ROLE_NOT_FOUND.get());

    Mockito.verify(bCryptPasswordEncoder, Mockito.never()).encode(password);
    Mockito.verify(userRepository, Mockito.never()).save(any(User.class));
  }

  @Test
  public void registrationFailedIllegalPasswordForEncryption() {
    String email = "foo@bar.baz";
    String password = null;

    Role role = new Role();
    role.setId(1L);
    role.setName("client");

    Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.empty());
    Mockito.when(roleRepository.findByName("client")).thenReturn(Optional.of(role));
    Mockito.when(bCryptPasswordEncoder.encode(password)).thenCallRealMethod();

    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      userService.registerUser(email, password);
    });

    Mockito.verify(userRepository, Mockito.never()).save(any(User.class));
  }
}