package com.example.demo.service;

import com.example.demo.constant.Error;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.mappers.UserMapper;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import java.util.Optional;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

  private final UserRepository userRepository;

  private final RoleRepository roleRepository;

  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  private UserMapper userMapper = Mappers.getMapper(UserMapper.class);

  @Autowired
  public UserService(
      UserRepository userRepository,
      RoleRepository roleRepository,
      BCryptPasswordEncoder bCryptPasswordEncoder
  ) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  @Transactional(isolation = Isolation.READ_COMMITTED)
  public User registerUser(String email, String password) throws Exception {

    if (findByEmail(email).isPresent()) {
      throw new Exception(Error.USER_REGISTERED.get());
    }

    Role clientRole = roleRepository.findByName("client")
        .orElseThrow(() -> new Exception(Error.ROLE_NOT_FOUND.get()));

    User user = new User();
    user.setEmail(email);
    user.setPassword(bCryptPasswordEncoder.encode(password));
    user.setRole(clientRole);

    return userRepository.save(user);
  }

  @Transactional(isolation = Isolation.READ_COMMITTED)
  public Optional<UserDto> findByEmail(String email) {
    return userRepository.findByEmail(email).map(userMapper::userToDto);
  }

  public boolean passwordsSame(String password1, String password2) {
    return bCryptPasswordEncoder.matches(password1, password2);
  }
}