package com.simbirsoft.simbircontrol.service;

import com.simbirsoft.simbircontrol.rest.dto.ReleaseRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.ReleaseResponseDto;

import java.util.List;

public interface ReleaseService {

    /**
     * get unfinished tasks by id release
     * @param id id release
     * @return list tasks
     */
    Integer getUnfinishedTasksById(Integer id);

    /**
     * get release by his id
     * @param id id release
     * @return release
     */
    ReleaseResponseDto getById(Integer id);

    /**
     * create new release
     * @param requestDto properties new release
     * @return new release
     */
    ReleaseResponseDto create(ReleaseRequestDto requestDto);

    /**
     * update old release new values
     * @param requestDto new values release
     * @return release
     */
    ReleaseResponseDto update(ReleaseRequestDto requestDto);

    /**
     * delete release by his id
     * @param id id release
     */
    void deleteById(Integer id);
}
