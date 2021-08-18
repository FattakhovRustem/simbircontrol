package com.simbirsoft.simbircontrol.rest;

import com.simbirsoft.simbircontrol.rest.dto.TaskRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.TaskResponseDto;
import com.simbirsoft.simbircontrol.service.TaskService;
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

@Tag(name = "Управление задачами")
@RestController
@RequestMapping("/task")
public class TaskController {

    private final static Logger logger = LoggerFactory.getLogger(TaskController.class);

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Operation(summary = "Получить список задач")
    @GetMapping(value = "/all")
    public ResponseEntity<List<TaskResponseDto>> getAllTasks() {
        logger.info("GET /task/all");
        List<TaskResponseDto> list = taskService.getAll();
        return ResponseEntity.ok().body(list);
    }

    @Operation(summary = "Получить задачу")
    @GetMapping(value = "/{id}")
    public ResponseEntity<TaskResponseDto> getTask(@PathVariable Integer id) {
        logger.info(String.format("GET /task id = %d", id));
        TaskResponseDto responseDto = taskService.getById(id);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Создать задачу")
    @PostMapping(value = "/create")
    public ResponseEntity<TaskResponseDto> createTask(@RequestBody TaskRequestDto requestDto) {
        logger.info("POST /task/create");
        logger.debug("TaskRequestDto {}", requestDto);
        TaskResponseDto responseDto = taskService.create(requestDto);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Изменить задачу")
    @PutMapping(value = "/update")
    public ResponseEntity<TaskResponseDto> updateTask(@RequestBody TaskRequestDto requestDto) {
        logger.info(String.format("PUT /task/update id = %d", requestDto.getId()));
        logger.debug("TaskRequestDto {}", requestDto);
        TaskResponseDto responseDto = taskService.update(requestDto);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Удалить задачу")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deleteTask(@PathVariable Integer id) {
        logger.info(String.format("DELETE /task/delete id = %d", id));
        taskService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity handleIOExcenption(IOException e) {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
