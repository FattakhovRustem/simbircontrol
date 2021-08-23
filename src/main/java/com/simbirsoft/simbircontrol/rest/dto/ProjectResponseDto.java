package com.simbirsoft.simbircontrol.rest.dto;

import com.simbirsoft.simbircontrol.enums.State;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Проект")
public class ProjectResponseDto {

    @Schema(description = "ID проекта")
    private Integer id;

    @Schema(description = "ID руководителя проекта")
    private Integer userIdLeader;

    @Schema(description = "Статус")
    private State state;

    @Schema(description = "Наименование проекта")
    private String name;

    @Schema(description = "ID клиента")
    private Integer clientId;

    @Schema(description = "Описание проекта")
    private String description;

    @Schema(description = "Стоимость проекта")
    private Long price;

    public ProjectResponseDto() {
    }

    public ProjectResponseDto(Integer id, Integer userIdLeader, State state, String name, Integer clientId, String description, Long price) {
        this.id = id;
        this.userIdLeader = userIdLeader;
        this.state = state;
        this.name = name;
        this.clientId = clientId;
        this.description = description;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserIdLeader() {
        return userIdLeader;
    }

    public void setUserIdLeader(Integer userIdLeader) {
        this.userIdLeader = userIdLeader;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        ProjectResponseDto responseDto = (ProjectResponseDto) obj;
        return this.getId() == responseDto.getId() && this.getClientId() == responseDto.getClientId() && this.getName().equals(responseDto.getName());
    }
}
