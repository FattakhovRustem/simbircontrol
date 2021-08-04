package com.simbirsoft.simbircontrol.service.impl;

import com.simbirsoft.simbircontrol.entity.Client;
import com.simbirsoft.simbircontrol.exception.NoEntityException;
import com.simbirsoft.simbircontrol.repository.ClientRepository;
import com.simbirsoft.simbircontrol.rest.dto.ClientRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.ClientResponseDto;
import com.simbirsoft.simbircontrol.service.ClientService;
import com.simbirsoft.simbircontrol.service.converter.ClientConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientConverter clientConverter;

    public ClientServiceImpl(ClientRepository clientRepository, ClientConverter clientConverter) {
        this.clientRepository = clientRepository;
        this.clientConverter = clientConverter;
    }

    @Transactional
    @Override
    public List<ClientResponseDto> getAll() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream().map(clientConverter::fromClientToClientResponseDto).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public ClientResponseDto getById(Integer id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new NoEntityException("Client not found"));
        return clientConverter.fromClientToClientResponseDto(client);
    }

    @Transactional
    @Override
    public ClientResponseDto create(ClientRequestDto requestDto) {
        Client client = clientRepository.save(clientConverter.fromClientRequestDtoToClient(requestDto));
        return clientConverter.fromClientToClientResponseDto(client);
    }

    @Transactional
    @Override
    public ClientResponseDto update(ClientRequestDto requestDto) {
        clientRepository.findById(requestDto.getId()).orElseThrow(() -> new NoEntityException("Client not found"));
        Client client = clientRepository.save(clientConverter.fromClientRequestDtoToClient(requestDto));
        return clientConverter.fromClientToClientResponseDto(client);
    }

    @Transactional
    @Override
    public void deleteById(Integer id) {
        clientRepository.findById(id).orElseThrow(() -> new NoEntityException("Client not found"));
        clientRepository.deleteById(id);
    }
}
