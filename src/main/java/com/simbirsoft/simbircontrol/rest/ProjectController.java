package com.simbirsoft.simbircontrol.rest;

import com.simbirsoft.simbircontrol.rest.dto.ProjectRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.ProjectResponseDto;
import com.simbirsoft.simbircontrol.rest.dto.ReleaseResponseDto;
import com.simbirsoft.simbircontrol.rest.dto.TaskResponseDto;
import com.simbirsoft.simbircontrol.service.ProjectService;
import com.simbirsoft.simbircontrol.service.filter.Condition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Tag(name = "Управление проектами")
@RestController
@RequestMapping("/project")
public class ProjectController {

    private final static Logger logger = LoggerFactory.getLogger(ProjectController.class);

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Operation(summary = "Получить список проектов")
    @GetMapping(value = "/all")
    public ResponseEntity<List<ProjectResponseDto>> getAllProjects() {
        logger.info("GET /project/all");
        List<ProjectResponseDto> list =  projectService.getAll();
        return ResponseEntity.ok().body(list);
    }

    @Operation(summary = "")
    @PostMapping(value = "/{id}/uploadcsv")
    public ResponseEntity uploadCSV( @PathVariable Integer id, @RequestParam("file") MultipartFile file) {
        logger.info(String.format("POST /project/{id}/uploadcsv id = %d and file = %s", id, file.getName()));
        projectService.addTasksFromCSV(id, file);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Получить список релизов проекта")
    @GetMapping(value = "/{id}/releases")
    public ResponseEntity<List<ReleaseResponseDto>> getReleasesProject(@PathVariable Integer id) {
        logger.info(String.format("GET /project/{id}/releases id = %d", id));
        return ResponseEntity.ok().body(projectService.getReleasesProject(id));
    }

    @Operation(summary = "Получить список задач проекта")
    @GetMapping(value = "/{id}/tasks")
    public ResponseEntity<List<TaskResponseDto>> getTasksProject(@PathVariable Integer id) {
        logger.info(String.format("GET /project/{id}/tasks id = %d", id));
        return ResponseEntity.ok().body(projectService.getTasksProject(id));
    }

    @Operation(summary = "Получить список задач проекта по фильтру")
    @GetMapping(value = "/{id}/tasks/filter")
    public ResponseEntity<List<TaskResponseDto>> getFilteredTasksProject(@PathVariable Integer id, @RequestBody List<Condition> conditions) {
        logger.info(String.format("GET /project/{id}/tasks/filter id = %d", id));
        logger.debug("List conditions {}", conditions);
        return ResponseEntity.ok().body(projectService.getFilteredTasksProject(id, conditions));
    }

    @Operation(summary = "Получить проект")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProjectResponseDto> getProject(@PathVariable Integer id) {
        logger.info(String.format("GET /project id = %d", id));
        ProjectResponseDto responseDto = projectService.getById(id);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Создать проект")
    @PostMapping(value = "/create")
    public ResponseEntity<ProjectResponseDto> createProject(@RequestBody ProjectRequestDto requestDto) {
        logger.info("POST /project/create");
        logger.debug("ProjectRequestDto {}", requestDto);
        ProjectResponseDto responseDto = projectService.create(requestDto);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Изменить проект")
    @PutMapping(value = "/update")
    public ResponseEntity<ProjectResponseDto> updateProject(@RequestBody ProjectRequestDto requestDto) {
        logger.info(String.format("PUT /project/update id = %d", requestDto.getId()));
        logger.debug("ProjectRequestDto {}", requestDto);
        ProjectResponseDto responseDto = projectService.update(requestDto);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Удалить проект")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deleteProject(@PathVariable Integer id) {
        logger.info(String.format("DELETE /project/delete id = %d", id));
        projectService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity handleIOExcenption(IOException e) {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
