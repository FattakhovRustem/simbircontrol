package com.simbirsoft.simbircontrol.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Проект")
public class ProjectResponseDto {

    @Schema(description = "ID проекта")
    private Integer id;

    @Schema(description = "Руководитель проекта")
    private UserResponseDto leader;

    @Schema(description = "Статус")
    private String state;

    @Schema(description = "Наименование проекта")
    private String name;

    @Schema(description = "Наименование клиента")
    private String clientName;

    @Schema(description = "Описание проекта")
    private String description;

    @Schema(description = "Задачи проекта")
    private List<TaskResponseDto> tasks;

    @Schema(description = "Релизы проекта")
    private List<ReleaseResponseDto> releases;

    public ProjectResponseDto() {
    }

    public ProjectResponseDto(Integer id, UserResponseDto leader, String state, String name, String clientName, String description, List<TaskResponseDto> tasks, List<ReleaseResponseDto> releases) {
        this.id = id;
        this.leader = leader;
        this.state = state;
        this.name = name;
        this.clientName = clientName;
        this.description = description;
        this.tasks = tasks;
        this.releases = releases;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserResponseDto getLeader() {
        return leader;
    }

    public void setLeader(UserResponseDto leader) {
        this.leader = leader;
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

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<TaskResponseDto> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskResponseDto> tasks) {
        this.tasks = tasks;
    }

    public List<ReleaseResponseDto> getReleases() {
        return releases;
    }

    public void setReleases(List<ReleaseResponseDto> releases) {
        this.releases = releases;
    }
}
