package com.simbirsoft.simbircontrol.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Клиент")
public class ClientRequestDto {

    @Schema(description = "ID клиента")
    private Integer id;

    @Schema(description = "Наименование клиента")
    private String name;

    @Schema(description = "Лицевой счет клиента")
    private Integer number;

    public ClientRequestDto() {
    }

    public ClientRequestDto(Integer id, String name, Integer number) {
        this.id = id;
        this.name = name;
        this.number = number;
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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "id = " + id +
                ", name = " + name +
                ", number = " + number;
    }
}
