package com.simbirsoft.simbircontrol.rest;

import com.simbirsoft.simbircontrol.entity.Project;
import com.simbirsoft.simbircontrol.rest.dto.ProjectRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.ProjectResponseDto;
import com.simbirsoft.simbircontrol.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ProjectService projectService;

    @Operation(summary = "Получить список проектов")
    @GetMapping(value = "/all")
    public ResponseEntity<List<ProjectResponseDto>> getAllProjects() {
        List<ProjectResponseDto> list =  projectService.getAll();
        return ResponseEntity.ok().body(list);
    }

    @Operation(summary = "Получить проект")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProjectResponseDto> getProject(@PathVariable Integer id) {
        ProjectResponseDto responseDto = projectService.getById(id);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Создать проект")
    @PostMapping(value = "/create")
    public ResponseEntity<ProjectResponseDto> createProject(@RequestBody ProjectRequestDto requestDto) {
        ProjectResponseDto responseDto = projectService.create(requestDto);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Изменить проект")
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<ProjectResponseDto> updateProject(@PathVariable Integer id, @RequestBody ProjectRequestDto requestDto) {
        ProjectResponseDto responseDto = projectService.updateById(id, requestDto);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Удалить проект")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deleteProject(@PathVariable Integer id) {
        projectService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity handleIOExcenption(IOException e) {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
