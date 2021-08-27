package com.simbirsoft.simbircontrol.service;

import com.simbirsoft.simbircontrol.rest.dto.UserRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.UserResponseDto;

import java.util.List;

public interface UserService {

    /**
     * get all users
     * @return list users
     */
    List<UserResponseDto> getAll();

    /**
     * get user by his id
     * @param id id user
     * @return user
     */
    UserResponseDto getById(Integer id);

    /**
     * create new user
     * @param requestDto properties new user
     * @return new user
     */
    UserResponseDto create(UserRequestDto requestDto);

    /**
     * update old user new values
     * @param requestDto new values user
     * @return user
     */
    UserResponseDto update(UserRequestDto requestDto);

    /**
     * delete user by his id
     * @param id id user
     */
    void deleteById(Integer id);
}
