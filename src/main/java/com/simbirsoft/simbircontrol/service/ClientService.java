package com.simbirsoft.simbircontrol.service;

import com.simbirsoft.simbircontrol.rest.dto.ClientRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.ClientResponseDto;

import java.util.List;

public interface ClientService {

    List<ClientResponseDto> getAll();

    ClientResponseDto getById(Integer id);

    ClientResponseDto create(ClientRequestDto requestDto);

    ClientResponseDto update(ClientRequestDto requestDto);

    void deleteById(Integer id);
}
