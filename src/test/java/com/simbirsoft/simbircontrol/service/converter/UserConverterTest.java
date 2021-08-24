package com.simbirsoft.simbircontrol.service.converter;

import com.simbirsoft.simbircontrol.entity.Usr;
import com.simbirsoft.simbircontrol.enums.Role;
import com.simbirsoft.simbircontrol.rest.dto.UsrRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.UsrResponseDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.annotation.Profile;

@RunWith(MockitoJUnitRunner.class)
@Profile("test")
public class UserConverterTest {

    @InjectMocks
    private UsrConverter usrConverter;

    private Usr user = new Usr();

    @Before
    public void init() {
        user.setId(100);
        user.setLogin("ivan");
        user.setRole(Role.ADMIN);
    }

    @Test
    public void fromUserRequestDtoToUserTest() {
        UsrRequestDto userRequestDto = new UsrRequestDto();
        userRequestDto.setId(100);
        userRequestDto.setLogin("ivan");
        userRequestDto.setRole(Role.ADMIN);
        Usr actual = usrConverter.fromUsrRequestDtoToUsr(userRequestDto);

        Assert.assertEquals(user, actual);
    }

    @Test
    public void fromUserToUserResponseDtoTest() {
        UsrResponseDto expected = new UsrResponseDto();
        expected.setId(100);
        expected.setLogin("ivan");
        expected.setRole(Role.ADMIN);
        UsrResponseDto actual = usrConverter.fromUsrToUsrResponseDto(user);

        Assert.assertEquals(expected, actual);
    }

}
