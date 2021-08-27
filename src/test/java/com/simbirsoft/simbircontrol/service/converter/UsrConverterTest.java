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
        usr.setName("Ivan");
        usr.setSurname("Ivanov");
    }

    @Test
    public void fromUsrRequestDtoToUsrTest() {
        UsrRequestDto usrRequestDto = new UsrRequestDto();
        usrRequestDto.setId(100);
        usrRequestDto.setLogin("ivan");
        usrRequestDto.setRole(Role.ADMIN);
        usrRequestDto.setName("Ivan");
        usrRequestDto.setSurname("Ivanov");
        Usr actual = usrConverter.fromUsrRequestDtoToUsr(usrRequestDto);

        Assert.assertEquals(usr.getId(), actual.getId());
        Assert.assertEquals(usr.getLogin(), actual.getLogin());
        Assert.assertEquals(usr.getRole(), actual.getRole());
        Assert.assertEquals(usr.getName(), actual.getName());
        Assert.assertEquals(usr.getSurname(), actual.getSurname());
    }

    @Test
    public void fromUsrToUsrResponseDtoTest() {
        UsrResponseDto expected = new UsrResponseDto();
        expected.setId(100);
        expected.setLogin("ivan");
        expected.setRole(Role.ADMIN);
        expected.setName("Ivan");
        expected.setSurname("Ivanov");
        UsrResponseDto actual = usrConverter.fromUsrToUsrResponseDto(usr);

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getLogin(), actual.getLogin());
        Assert.assertEquals(expected.getRole(), actual.getRole());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getSurname(), actual.getSurname());
    }

}
