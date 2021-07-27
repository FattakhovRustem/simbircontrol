package com.simbirsoft.simbircontrol.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Проект")
public class ProjectResponseDto {

    @Schema(description = "ID проекта")
    private Integer id;

    @Schema(description = "ID руководителя проекта")
    private Integer userIdLeader;

    @Schema(description = "Статус")
    private String state;

    @Schema(description = "Наименование проекта")
    private String name;

    @Schema(description = "ID клиента")
    private Integer clientId;

    @Schema(description = "Описание проекта")
    private String description;

    public ProjectResponseDto() {
    }

    public ProjectResponseDto(Integer id, Integer userIdLeader, String state, String name, Integer clientId, String description) {
        this.id = id;
        this.userIdLeader = userIdLeader;
        this.state = state;
        this.name = name;
        this.clientId = clientId;
        this.description = description;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
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
}
