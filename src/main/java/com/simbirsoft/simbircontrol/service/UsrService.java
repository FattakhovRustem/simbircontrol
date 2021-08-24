package com.simbirsoft.simbircontrol.service;

import com.simbirsoft.simbircontrol.rest.dto.UsrRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.UsrResponseDto;

import java.util.List;

public interface UsrService {

    /**
     * get all users
     * @return list users
     */
    List<UsrResponseDto> getAll();

    /**
     * get user by his id
     * @param id id user
     * @return user
     */
    UsrResponseDto getById(Integer id);

    /**
     * create new user
     * @param requestDto properties new user
     * @return new user
     */
    UsrResponseDto create(UsrRequestDto requestDto);

    /**
     * update old user new values
     * @param requestDto new values user
     * @return user
     */
    UsrResponseDto update(UsrRequestDto requestDto);

    /**
     * delete user by his id
     * @param id id user
     */
    void deleteById(Integer id);
}
