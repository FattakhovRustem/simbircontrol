package com.simbirsoft.simbircontrol.service.converter;

import com.simbirsoft.simbircontrol.entity.Usr;
import com.simbirsoft.simbircontrol.rest.dto.UsrRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.UsrResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UsrConverter {

    public Usr fromUsrRequestDtoToUsr(UsrRequestDto requestDto) {
        Usr usr = new Usr();
        usr.setId(requestDto.getId());
        usr.setLogin(requestDto.getLogin());
        usr.setName(requestDto.getName());
        usr.setPassword(requestDto.getPassword());
        usr.setRole(requestDto.getRole());
        usr.setSurname(requestDto.getSurname());
        return usr;
    }

    public UsrResponseDto fromUsrToUsrResponseDto(Usr usr) {
        UsrResponseDto responseDto = new UsrResponseDto();
        responseDto.setId(usr.getId());
        responseDto.setLogin(usr.getLogin());
        responseDto.setName(usr.getName());
        responseDto.setPassword(usr.getPassword());
        responseDto.setRole(usr.getRole());
        responseDto.setSurname(usr.getSurname());
        return responseDto;
    }
}
