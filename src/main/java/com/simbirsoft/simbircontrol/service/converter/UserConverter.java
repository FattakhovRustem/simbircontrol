package com.simbirsoft.simbircontrol.service.converter;

import com.simbirsoft.simbircontrol.entity.User;
import com.simbirsoft.simbircontrol.rest.dto.UserRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.UserResponseDto;

public class UserConverter {

    public static User fromUserRequestDtoToUser(UserRequestDto requestDto) {
        User user = new User();
        user.setId(requestDto.getId());
        user.setLogin(requestDto.getLogin());
        user.setName(requestDto.getName());
        user.setPassword(requestDto.getPassword());
        user.setRole(requestDto.getRole());
        user.setSurname(requestDto.getSurname());
        return user;
    }

    public static UserResponseDto fromUserToUserResponseDto(User user) {
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(user.getId());
        responseDto.setLogin(user.getLogin());
        responseDto.setName(user.getName());
        responseDto.setPassword(user.getPassword());
        responseDto.setRole(user.getRole());
        responseDto.setSurname(user.getSurname());
        return responseDto;
    }
}
