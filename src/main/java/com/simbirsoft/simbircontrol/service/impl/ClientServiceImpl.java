package com.simbirsoft.simbircontrol.service.impl;

import com.simbirsoft.simbircontrol.repository.ClientRepository;
import com.simbirsoft.simbircontrol.rest.dto.ClientRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.ClientResponseDto;
import com.simbirsoft.simbircontrol.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository repository;

    @Override
    public List<ClientResponseDto> getAll() {
        return null;
    }

    @Override
    public ClientResponseDto getById(Integer id) {
        return null;
    }

    @Override
    public ClientResponseDto create(ClientRequestDto requestDto) {
        return null;
    }

    @Override
    public ClientResponseDto updateById(Integer id, ClientRequestDto requestDto) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
