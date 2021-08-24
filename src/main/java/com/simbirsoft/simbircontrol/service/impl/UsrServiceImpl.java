package com.simbirsoft.simbircontrol.service.impl;

import com.simbirsoft.simbircontrol.entity.Usr;
import com.simbirsoft.simbircontrol.exception.NoEntityException;
import com.simbirsoft.simbircontrol.repository.UsrRepository;
import com.simbirsoft.simbircontrol.rest.dto.UsrRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.UsrResponseDto;
import com.simbirsoft.simbircontrol.service.UsrService;
import com.simbirsoft.simbircontrol.service.converter.UsrConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Service
public class UsrServiceImpl implements UsrService {

    public static final Logger logger = LoggerFactory.getLogger(UsrServiceImpl.class);

    private final UsrRepository usrRepository;
    private final UsrConverter usrConverter;

    public UsrServiceImpl(UsrRepository usrRepository, UsrConverter usrConverter) {
        this.usrRepository = usrRepository;
        this.usrConverter = usrConverter;
    }

    @Transactional
    @Override
    public List<UsrResponseDto> getAll() {
        List<Usr> usrs = usrRepository.findAll();
        return usrs.stream().map(usrConverter::fromUsrToUsrResponseDto).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public UsrResponseDto getById(Integer id) {
        Usr usr = usrRepository.findById(id).orElseThrow(() -> {
            logger.error(String.format("getById - User with ID = %d not found", id));
            return new NoEntityException(String.format(ResourceBundle.getBundle("resource").getString("userNotFound"), id));
        });
        return usrConverter.fromUsrToUsrResponseDto(usr);
    }

    @Transactional
    @Override
    public UsrResponseDto create(UsrRequestDto requestDto) {
        Usr usr = usrRepository.save(usrConverter.fromUsrRequestDtoToUsr(requestDto));
        return usrConverter.fromUsrToUsrResponseDto(usr);
    }

    @Transactional
    @Override
    public UsrResponseDto update(UsrRequestDto requestDto) {
        usrRepository.findById(requestDto.getId()).orElseThrow(() -> {
            logger.error(String.format("update - User with ID = %d not found", requestDto.getId()));
            return new NoEntityException(String.format(ResourceBundle.getBundle("resource").getString("userNotFound"), requestDto.getId()));
        });
        Usr usr = usrRepository.save(usrConverter.fromUsrRequestDtoToUsr(requestDto));
        return usrConverter.fromUsrToUsrResponseDto(usr);
    }

    @Transactional
    @Override
    public void deleteById(Integer id) {
        usrRepository.findById(id).orElseThrow(() -> {
            logger.error(String.format("deleteById - Usr with ID = %d not found", id));
            return new NoEntityException(String.format(ResourceBundle.getBundle("resource").getString("userNotFound"), id));
        });
        usrRepository.deleteById(id);
    }

}
