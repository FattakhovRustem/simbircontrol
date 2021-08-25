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
public class UsrConverterTest {

    @InjectMocks
    private UsrConverter usrConverter;

    private Usr usr = new Usr();

    @Before
    public void init() {
        usr.setId(100);
        usr.setLogin("ivan");
        usr.setRole(Role.ADMIN);
    }

    @Test
    public void fromUsrRequestDtoToUsrTest() {
        UsrRequestDto usrRequestDto = new UsrRequestDto();
        usrRequestDto.setId(100);
        usrRequestDto.setLogin("ivan");
        usrRequestDto.setRole(Role.ADMIN);
        Usr actual = usrConverter.fromUsrRequestDtoToUsr(usrRequestDto);

        Assert.assertEquals(usr, actual);
    }

    @Test
    public void fromUsrToUsrResponseDtoTest() {
        UsrResponseDto expected = new UsrResponseDto();
        expected.setId(100);
        expected.setLogin("ivan");
        expected.setRole(Role.ADMIN);
        UsrResponseDto actual = usrConverter.fromUsrToUsrResponseDto(usr);

        Assert.assertEquals(expected, actual);
    }

}
