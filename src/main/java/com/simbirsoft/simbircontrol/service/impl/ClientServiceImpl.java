package com.simbirsoft.simbircontrol.service.impl;

import com.simbirsoft.simbircontrol.entity.Client;
import com.simbirsoft.simbircontrol.repository.ClientRepository;
import com.simbirsoft.simbircontrol.rest.dto.ClientRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.ClientResponseDto;
import com.simbirsoft.simbircontrol.service.ClientService;
import com.simbirsoft.simbircontrol.service.converter.ClientConverter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientConverter clientConverter;

    public ClientServiceImpl(ClientRepository clientRepository, ClientConverter clientConverter) {
        this.clientRepository = clientRepository;
        this.clientConverter = clientConverter;
    }

    @Override
    public List<ClientResponseDto> getAll() {
        List<ClientResponseDto> result = new ArrayList<>();
        List<Client> clients = clientRepository.findAll();
        for (Client client : clients) {
            result.add(clientConverter.fromClientToClientResponseDto(client));
        }
        return result;
    }

    @Override
    public ClientResponseDto getById(Integer id) {
        return clientConverter.fromClientToClientResponseDto(clientRepository.getById(id));
    }

    @Override
    public ClientResponseDto create(ClientRequestDto requestDto) {
        Client client = clientRepository.save(clientConverter.fromClientRequestDtoToClient(requestDto));
        return clientConverter.fromClientToClientResponseDto(client);
    }

    @Override
    public ClientResponseDto update(ClientRequestDto requestDto) {
        Client client = clientRepository.save(clientConverter.fromClientRequestDtoToClient(requestDto));
        return clientConverter.fromClientToClientResponseDto(client);
    }

    @Override
    public void deleteById(Integer id) {
        clientRepository.deleteById(id);
    }
}
