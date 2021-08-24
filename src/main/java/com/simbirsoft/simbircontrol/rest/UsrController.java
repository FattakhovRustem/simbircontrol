package com.simbirsoft.simbircontrol.rest;

import com.simbirsoft.simbircontrol.exception.NoEntityException;
import com.simbirsoft.simbircontrol.rest.dto.UsrRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.UsrResponseDto;
import com.simbirsoft.simbircontrol.service.UsrService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.util.List;

@Tag(name = "Управление пользователями")
@RestController
@RequestMapping(value = "/usr")
public class UsrController {

    private final static Logger logger = LoggerFactory.getLogger(UsrController.class);

    private final UsrService usrService;

    public UsrController(UsrService usrService) {
        this.usrService = usrService;
    }

    @Operation(summary = "Получить список пользователей")
    @GetMapping(value = "/all")
    public ResponseEntity<List<UsrResponseDto>> getAllUsrs() {
        logger.info("GET /usr/all");
        List<UsrResponseDto> list = usrService.getAll();
        return ResponseEntity.ok().body(list);
    }

    @Operation(summary = "Получить пользователя")
    @GetMapping(value = "/{id}")
    public ResponseEntity<UsrResponseDto> getUsr(@PathVariable Integer id) {
        logger.info(String.format("GET /usr id = %d", id));
        UsrResponseDto responseDto = usrService.getById(id);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Создать пользователя")
    @PostMapping(value = "/create")
    public ResponseEntity<UsrResponseDto> createUsr(@RequestBody UsrRequestDto requestDto) {
        logger.info("POST /usr/create");
        logger.debug("UsrRequestDto {}", requestDto);
        UsrResponseDto responseDto = usrService.create(requestDto);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Изменить пользователя")
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<UsrResponseDto> updateUsr(@RequestBody UsrRequestDto requestDto) {
        logger.info(String.format("PUT /usr/update id = %d", requestDto.getId()));
        logger.debug("UsrRequestDto {}", requestDto);
        UsrResponseDto responseDto = usrService.update(requestDto);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Удалить пользователя")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deleteUsr(@PathVariable Integer id) {
        logger.info(String.format("DELETE /usr/delete id = %d", id));
        usrService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity handleIOExcenption(IOException e) {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoEntityException.class)
    public ResponseEntity handleNoEntityException(NoEntityException e) {
        return ResponseEntity.ok().body(e.getMessage());
    }
}
