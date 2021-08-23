package com.simbirsoft.simbircontrol.service.converter;

import com.simbirsoft.simbircontrol.entity.User;
import com.simbirsoft.simbircontrol.enums.Role;
import com.simbirsoft.simbircontrol.rest.dto.UserRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.UserResponseDto;
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
    private UserConverter userConverter;

    private User user = new User();

    @Before
    public void init() {
        user.setId(100);
        user.setLogin("ivan");
        user.setRole(Role.ADMIN);
    }

    @Test
    public void fromUserRequestDtoToUserTest() {
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setId(100);
        userRequestDto.setLogin("ivan");
        userRequestDto.setRole(Role.ADMIN);
        User actual = userConverter.fromUserRequestDtoToUser(userRequestDto);

        Assert.assertEquals(user, actual);
    }

    @Test
    public void fromUserToUserResponseDtoTest() {
        UserResponseDto expected = new UserResponseDto();
        expected.setId(100);
        expected.setLogin("ivan");
        expected.setRole(Role.ADMIN);
        UserResponseDto actual = userConverter.fromUserToUserResponseDto(user);

        Assert.assertEquals(expected, actual);
    }

}
