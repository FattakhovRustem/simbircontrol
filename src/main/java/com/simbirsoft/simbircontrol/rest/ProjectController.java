package com.simbirsoft.simbircontrol.rest;

import com.simbirsoft.simbircontrol.rest.dto.ProjectRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.ProjectResponseDto;
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

@Tag(name = "Управление проектами")
@RestController
@RequestMapping("/project")
public class ProjectController {

    @Operation(summary = "Получить список проектов")
    @GetMapping(value = "/all")
    public ResponseEntity<List<ProjectResponseDto>> getAllProjects() {
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Создать проект")
    @PostMapping(value = "/create")
    public ResponseEntity<ProjectResponseDto> createProject(@RequestBody ProjectRequestDto requestDto) {
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Изменить проект")
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<ProjectResponseDto> updateProject(@PathVariable Integer id, @RequestBody ProjectRequestDto requestDto) {
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Удалить проект")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deleteProject(@PathVariable Integer id) {
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity handleIOExcenption(IOException e) {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
