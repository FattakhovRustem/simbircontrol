package com.simbirsoft.simbircontrol.rest;

import com.simbirsoft.simbircontrol.rest.dto.TaskRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.TaskResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Tag(name = "Управление задачами")
@RestController
@RequestMapping("/task")
public class TaskController {

    @Operation(summary = "Получить список задач")
    @GetMapping(value = "/all")
    public ResponseEntity<List<TaskResponseDto>> getAllTasks() {
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Создать задачу")
    @PostMapping(value = "/create")
    public ResponseEntity<TaskResponseDto> createTask(@RequestBody TaskRequestDto requestDto) {
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Изменить задачу")
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<TaskResponseDto> updateTask(@PathVariable Integer id, @RequestBody TaskRequestDto requestDto) {
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Удалить задачу")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deleteTask(@PathVariable Integer id) {
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity handleIOExcenption(IOException e) {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
