package com.simbirsoft.simbircontrol.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Клиент")
public class ClientRequestDto {

    @Schema(description = "ID клиента")
    private Integer id;

    @Schema(description = "Наименование клиента")
    private String name;

    public ClientRequestDto() {
    }

    public ClientRequestDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
