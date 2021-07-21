package com.simbirsoft.simbircontrol.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Задача")
public class TaskResponseDto {

    @Schema(description = "ID задачи")
    private Integer id;

    @Schema(description = "Статус")
    private String state;

    @Schema(description = "Релиз")
    private ReleaseResponseDto release;

    @Schema(description = "Автор")
    private UserResponseDto author;

    @Schema(description = "Исполнитель")
    private UserResponseDto performer;

    @Schema(description = "Название задачи")
    private String name;

    //@Schema(description = "ID проекта")
    //private Integer projectId;

    @Schema(description = "Описание задачи")
    private String description;

    public TaskResponseDto() {
    }

    public TaskResponseDto(Integer id, String state, ReleaseResponseDto release, UserResponseDto author, UserResponseDto performer, String name, String description) {
        this.id = id;
        this.state = state;
        this.release = release;
        this.author = author;
        this.performer = performer;
        this.name = name;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public ReleaseResponseDto getRelease() {
        return release;
    }

    public void setRelease(ReleaseResponseDto release) {
        this.release = release;
    }

    public UserResponseDto getAuthor() {
        return author;
    }

    public void setAuthor(UserResponseDto author) {
        this.author = author;
    }

    public UserResponseDto getPerformer() {
        return performer;
    }

    public void setPerformer(UserResponseDto performer) {
        this.performer = performer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
