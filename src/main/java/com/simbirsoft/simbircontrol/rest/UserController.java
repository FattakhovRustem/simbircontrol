package com.simbirsoft.simbircontrol.rest;

import com.simbirsoft.simbircontrol.rest.dto.UserRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.UserResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/user")
public class UserController {

    @Operation(summary = "Получить список пользователей")
    @GetMapping(value = "/all")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Создать пользователя")
    @PostMapping(value = "/create")
    public ResponseEntity<UserResponseDto> createTask(@RequestBody UserRequestDto requestDto) {
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Изменить пользователя")
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Integer id, @RequestBody UserRequestDto requestDto) {
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Удалить пользователя")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity handleIOExcenption(IOException e) {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
