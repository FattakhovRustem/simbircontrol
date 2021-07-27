package com.simbirsoft.simbircontrol.rest;

import com.simbirsoft.simbircontrol.rest.dto.ReleaseRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.ReleaseResponseDto;
import com.simbirsoft.simbircontrol.service.ReleaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;

@Tag(name = "Управление релизами")
@RestController
@RequestMapping("/release")
public class ReleaseController {

    @Autowired
    private ReleaseService releaseService;

    @Operation(summary = "Получить релиз")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ReleaseResponseDto> getRelease(@PathVariable Integer id) {
        ReleaseResponseDto responseDto = releaseService.getById(id);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Создать релиз")
    @PostMapping(value = "/create")
    public ResponseEntity<ReleaseResponseDto> createRelease(@RequestBody ReleaseRequestDto requestDto) {
        ReleaseResponseDto responseDto = releaseService.create(requestDto);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Изменить релиз")
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<ReleaseResponseDto> updateRelease(@PathVariable Integer id, @RequestBody ReleaseRequestDto requestDto) {
        ReleaseResponseDto responseDto = releaseService.updateById(id, requestDto);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Удалить релиз")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deleteRelease(@PathVariable Integer id) {
        releaseService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity handleIOExcenption(IOException e) {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
