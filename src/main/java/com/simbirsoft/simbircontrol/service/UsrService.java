package com.simbirsoft.simbircontrol.service;

import com.simbirsoft.simbircontrol.rest.dto.UsrRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.UsrResponseDto;

import java.util.List;

public interface UsrService {

    /**
     * get all usrs
     * @return list usrs
     */
    List<UsrResponseDto> getAll();

    /**
     * get usr by his id
     * @param id id usr
     * @return usr
     */
    UsrResponseDto getById(Integer id);

    /**
     * create new usr
     * @param requestDto properties new usr
     * @return new usr
     */
    UsrResponseDto create(UsrRequestDto requestDto);

    /**
     * update old usr new values
     * @param requestDto new values usr
     * @return usr
     */
    UsrResponseDto update(UsrRequestDto requestDto);

    /**
     * delete usr by his id
     * @param id id usr
     */
    void deleteById(Integer id);
}
