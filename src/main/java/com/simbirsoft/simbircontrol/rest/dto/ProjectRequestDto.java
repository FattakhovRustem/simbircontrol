package com.simbirsoft.simbircontrol.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Проект")
public class ProjectRequestDto {

    @Schema(description = "ID проекта")
    private Integer id;

    @Schema(description = "Руководитель проекта")
    private UserRequestDto leader;

    @Schema(description = "Статус")
    private String state;

    @Schema(description = "Наименование проекта")
    private String name;

    @Schema(description = "Наименование клиента")
    private String clientName;

    @Schema(description = "Описание проекта")
    private String description;

    @Schema(description = "Задачи проекта")
    private List<TaskRequestDto> tasks;

    @Schema(description = "Релизы проекта")
    private List<ReleaseRequestDto> releases;

    public ProjectRequestDto() {
    }

    public ProjectRequestDto(Integer id, UserRequestDto leader, String state, String name, String clientName, String description, List<TaskRequestDto> tasks, List<ReleaseRequestDto> releases) {
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

    public UserRequestDto getLeader() {
        return leader;
    }

    public void setLeader(UserRequestDto leader) {
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

    public List<TaskRequestDto> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskRequestDto> tasks) {
        this.tasks = tasks;
    }

    public List<ReleaseRequestDto> getReleases() {
        return releases;
    }

    public void setReleases(List<ReleaseRequestDto> releases) {
        this.releases = releases;
    }
}
