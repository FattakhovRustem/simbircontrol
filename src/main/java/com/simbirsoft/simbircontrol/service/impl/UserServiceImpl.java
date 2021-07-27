package com.simbirsoft.simbircontrol.service.impl;

import com.simbirsoft.simbircontrol.repository.UserRepository;
import com.simbirsoft.simbircontrol.rest.dto.UserRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.UserResponseDto;
import com.simbirsoft.simbircontrol.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserResponseDto> getAll() {
        return null;
    }

    @Override
    public UserResponseDto getById(Integer id) {
        return null;
    }

    @Override
    public UserResponseDto create(UserRequestDto requestDto) {
        return null;
    }

    @Override
    public UserResponseDto updateById(Integer id, UserRequestDto requestDto) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
