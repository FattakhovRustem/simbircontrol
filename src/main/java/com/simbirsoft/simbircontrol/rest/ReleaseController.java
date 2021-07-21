package com.simbirsoft.simbircontrol.rest;

import com.simbirsoft.simbircontrol.rest.dto.ReleaseRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.ReleaseResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Tag(name = "Управление релизами")
@RestController
@RequestMapping("/release")
public class ReleaseController {

    //@Operation(summary = "Получить список релизов")
    //@Operation(summary = "Получить список релизов проекта")
    //@Operation(summary = "Получить релизов задачи")


    @Operation(summary = "Создать релиз")
    @PostMapping(value = "/create")
    public ResponseEntity<ReleaseResponseDto> createRelease(@RequestBody ReleaseRequestDto requestDto) {
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Изменить релиз")
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<ReleaseResponseDto> updateRelease(@PathVariable Integer id, @RequestBody ReleaseRequestDto requestDto) {
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Удалить релиз")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deleteRelease(@PathVariable Integer id) {
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity handleIOExcenption(IOException e) {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
