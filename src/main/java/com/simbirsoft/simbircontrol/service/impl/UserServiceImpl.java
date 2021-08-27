package com.simbirsoft.simbircontrol.service.impl;

import com.simbirsoft.simbircontrol.entity.User;
import com.simbirsoft.simbircontrol.exception.NoEntityException;
import com.simbirsoft.simbircontrol.repository.UserRepository;
import com.simbirsoft.simbircontrol.rest.dto.UserRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.UserResponseDto;
import com.simbirsoft.simbircontrol.service.UserService;
import com.simbirsoft.simbircontrol.service.converter.UserConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    public static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    @Transactional
    @Override
    public List<UserResponseDto> getAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userConverter::fromUserToUserResponseDto).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public UserResponseDto getById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            logger.error(String.format("getById - User with ID = %d not found", id));
            return new NoEntityException(String.format("User with ID = %d not found", id));
        });
        return userConverter.fromUserToUserResponseDto(user);
    }

    @Transactional
    @Override
    public UserResponseDto create(UserRequestDto requestDto) {
        User user = userRepository.save(userConverter.fromUserRequestDtoToUser(requestDto));
        return userConverter.fromUserToUserResponseDto(user);
    }

    @Transactional
    @Override
    public UserResponseDto update(UserRequestDto requestDto) {
        userRepository.findById(requestDto.getId()).orElseThrow(() -> {
            logger.error(String.format("update - User with ID = %d not found", requestDto.getId()));
            return new NoEntityException(String.format("User with ID = %d not found", requestDto.getId()));
        });
        User user = userRepository.save(userConverter.fromUserRequestDtoToUser(requestDto));
        return userConverter.fromUserToUserResponseDto(user);
    }

    @Transactional
    @Override
    public void deleteById(Integer id) {
        userRepository.findById(id).orElseThrow(() -> {
            logger.error(String.format("deleteById - User with ID = %d not found", id));
            return new NoEntityException(String.format("User with ID = %d not found", id));
        });
        userRepository.deleteById(id);
    }

}
