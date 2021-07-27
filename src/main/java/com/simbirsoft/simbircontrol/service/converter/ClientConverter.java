package com.simbirsoft.simbircontrol.service.converter;

import com.simbirsoft.simbircontrol.entity.Client;
import com.simbirsoft.simbircontrol.rest.dto.ClientRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.ClientResponseDto;

public class ClientConverter {

    public static Client fromClientRequestDtoToClient(ClientRequestDto requestDto) {
        Client client = new Client();

        return client;
    }

    public static ClientResponseDto fromClientToClientResponseDto(Client client) {
        ClientResponseDto responseDto = new ClientResponseDto();

        return responseDto;
    }
}
