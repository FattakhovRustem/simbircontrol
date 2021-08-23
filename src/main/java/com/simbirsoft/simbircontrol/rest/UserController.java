package com.simbirsoft.simbircontrol.rest;

import com.simbirsoft.simbircontrol.exception.NoEntityException;
import com.simbirsoft.simbircontrol.rest.dto.UserRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.UserResponseDto;
import com.simbirsoft.simbircontrol.service.UserService;
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
@RequestMapping(value = "/user")
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Получить список пользователей")
    @GetMapping(value = "/all")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        logger.info("GET /user/all");
        List<UserResponseDto> list = userService.getAll();
        return ResponseEntity.ok().body(list);
    }

    @Operation(summary = "Получить пользователя")
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Integer id) {
        logger.info(String.format("GET /user id = %d", id));
        UserResponseDto responseDto = userService.getById(id);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Создать пользователя")
    @PostMapping(value = "/create")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto requestDto) {
        logger.info("POST /user/create");
        logger.debug("UserRequestDto {}", requestDto);
        UserResponseDto responseDto = userService.create(requestDto);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Изменить пользователя")
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@RequestBody UserRequestDto requestDto) {
        logger.info(String.format("PUT /user/update id = %d", requestDto.getId()));
        logger.debug("UserRequestDto {}", requestDto);
        UserResponseDto responseDto = userService.update(requestDto);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Удалить пользователя")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        logger.info(String.format("DELETE /user/delete id = %d", id));
        userService.deleteById(id);
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
