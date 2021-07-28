package com.simbirsoft.simbircontrol.service.impl;

import com.simbirsoft.simbircontrol.entity.User;
import com.simbirsoft.simbircontrol.repository.UserRepository;
import com.simbirsoft.simbircontrol.rest.dto.UserRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.UserResponseDto;
import com.simbirsoft.simbircontrol.service.UserService;
import com.simbirsoft.simbircontrol.service.converter.UserConverter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserConverter userConverter;

    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    @Override
    public List<UserResponseDto> getAll() {
        List<UserResponseDto> result = new ArrayList<>();
        List<User> users = userRepository.findAll();
        System.out.println(users.size());
        for (User user : users) {
            result.add(userConverter.fromUserToUserResponseDto(user));
        }
        return result;
    }

    @Override
    public UserResponseDto getById(Integer id) {
        return userConverter.fromUserToUserResponseDto(userRepository.getById(id));
    }

    @Override
    public UserResponseDto create(UserRequestDto requestDto) {
        User user = userRepository.save(userConverter.fromUserRequestDtoToUser(requestDto));
        return userConverter.fromUserToUserResponseDto(user);
    }

    @Override
    public UserResponseDto update(UserRequestDto requestDto) {
        User user = userRepository.save(userConverter.fromUserRequestDtoToUser(requestDto));
        return userConverter.fromUserToUserResponseDto(user);
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }
}
