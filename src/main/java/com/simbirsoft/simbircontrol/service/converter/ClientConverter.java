package com.simbirsoft.simbircontrol.service.converter;

import com.simbirsoft.simbircontrol.entity.Client;
import com.simbirsoft.simbircontrol.rest.dto.ClientRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.ClientResponseDto;
import org.springframework.stereotype.Component;

@Component
public class ClientConverter {

    public Client fromClientRequestDtoToClient(ClientRequestDto requestDto) {
        Client client = new Client();
        client.setId(requestDto.getId());
        client.setName(requestDto.getName());
        client.setNumber(requestDto.getNumber());
        return client;
    }

    public ClientResponseDto fromClientToClientResponseDto(Client client) {
        ClientResponseDto responseDto = new ClientResponseDto();
        responseDto.setId(client.getId());
        responseDto.setName(client.getName());
        responseDto.setNumber(client.getNumber());
        return responseDto;
    }
}
