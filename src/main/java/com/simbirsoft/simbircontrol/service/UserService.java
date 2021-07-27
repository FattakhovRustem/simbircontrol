package com.simbirsoft.simbircontrol.service;

import com.simbirsoft.simbircontrol.rest.dto.UserRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.UserResponseDto;

import java.util.List;

public interface UserService {

    List<UserResponseDto> getAll();

    UserResponseDto getById(Integer id);

    UserResponseDto create(UserRequestDto requestDto);

    UserResponseDto updateById(Integer id, UserRequestDto requestDto);

    void deleteById(Integer id);
}
