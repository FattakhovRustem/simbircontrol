package com.simbirsoft.simbircontrol.rest;

import com.simbirsoft.simbircontrol.rest.dto.ClientRequestDto;
import com.simbirsoft.simbircontrol.rest.dto.ClientResponseDto;
import com.simbirsoft.simbircontrol.service.ClientService;
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

@Tag(name = "Управление клиентами")
@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Operation(summary = "Получить список клиентов")
    @GetMapping(value = "/all")
    public ResponseEntity<List<ClientResponseDto>> getAllClients() {
        List<ClientResponseDto> list =  clientService.getAll();
        return ResponseEntity.ok().body(list);
    }

    @Operation(summary = "Получить клиента")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientResponseDto> getClient(@PathVariable Integer id) {
        ClientResponseDto responseDto = clientService.getById(id);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Создать клиента")
    @PostMapping(value = "/create")
    public ResponseEntity<ClientResponseDto> createClient(@RequestBody ClientRequestDto requestDto) {
        ClientResponseDto responseDto = clientService.create(requestDto);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Изменить клиента")
    @PutMapping(value = "/update")
    public ResponseEntity<ClientResponseDto> updateClient(@RequestBody  ClientRequestDto requestDto) {
        ClientResponseDto responseDto = clientService.update(requestDto);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Удалить клиента")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deleteClient(@PathVariable Integer id) {
        clientService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity handleIOException(IOException e) {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

}