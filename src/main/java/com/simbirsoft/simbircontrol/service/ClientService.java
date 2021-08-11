package com.simbirsoft.simbircontrol.service;

import com.simbirsoft.simbircontrol.rest.dto.ClientRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.ClientResponseDto;

import java.util.List;

public interface ClientService {

    /**
     * get all clients
     * @return list clients
     */
    List<ClientResponseDto> getAll();

    /**
     * get client by his id
     * @param id id client
     * @return client
     */
    ClientResponseDto getById(Integer id);

    /**
     * create new client
     * @param requestDto properties new client
     * @return new client
     */
    ClientResponseDto create(ClientRequestDto requestDto);

    /**
     * update old client new values
     * @param requestDto new values client
     * @return client
     */
    ClientResponseDto update(ClientRequestDto requestDto);

    /**
     * delete client by his id
     * @param id id client
     */
    void deleteById(Integer id);
}
