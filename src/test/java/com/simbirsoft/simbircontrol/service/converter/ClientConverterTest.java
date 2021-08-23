package com.simbirsoft.simbircontrol.service.converter;

import com.simbirsoft.simbircontrol.entity.Client;
import com.simbirsoft.simbircontrol.rest.dto.ClientRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.ClientResponseDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.annotation.Profile;

@RunWith(MockitoJUnitRunner.class)
@Profile("test")
public class ClientConverterTest {

    @InjectMocks
    private ClientConverter clientConverter;

    private final Client client = new Client();

    @Before
    public void init() {
        client.setId(10);
        client.setNumber(103456);
        client.setName("SimbirSoft");
    }

    @Test
    public void fromClientRequestDtoToClientTest() {
        ClientRequestDto clientRequestDto = new ClientRequestDto();
        clientRequestDto.setId(10);
        clientRequestDto.setNumber(103456);
        clientRequestDto.setName("SimbirSoft");
        Client actual = clientConverter.fromClientRequestDtoToClient(clientRequestDto);

        Assert.assertEquals(client, actual);
    }

    @Test
    public void fromClientToClientResponseDtoTest() {
        ClientResponseDto expected = new ClientResponseDto();
        expected.setId(10);
        expected.setNumber(103456);
        expected.setName("SimbirSoft");
        ClientResponseDto actual = clientConverter.fromClientToClientResponseDto(client);
        Assert.assertEquals(expected, actual);
    }
}
