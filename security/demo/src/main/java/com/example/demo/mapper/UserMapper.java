package com.example.demo.mapper;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

  UserDto userToDto(User user);
}
