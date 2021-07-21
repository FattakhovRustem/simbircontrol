package com.simbirsoft.simbircontrol.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.text.DecimalFormat;

@Schema(description = "Задача")
public class TaskRequestDto {

    @Schema(description = "ID задачи")
    private Integer id;

    @Schema(description = "Статус")
    private String state;

    @Schema(description = "Релиз")
    private ReleaseRequestDto release;

    @Schema(description = "Автор")
    private UserRequestDto author;

    @Schema(description = "Исполнитель")
    private UserRequestDto performer;

    @Schema(description = "Название задачи")
    private String name;

    //@Schema(description = "ID проекта")
    //private Integer projectId;

    @Schema(description = "Описание задачи")
    private String description;

    public TaskRequestDto() {
    }

    public TaskRequestDto(Integer id, String state, ReleaseRequestDto release, UserRequestDto author, UserRequestDto performer, String name, String description) {
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

    public ReleaseRequestDto getRelease() {
        return release;
    }

    public void setRelease(ReleaseRequestDto release) {
        this.release = release;
    }

    public UserRequestDto getAuthor() {
        return author;
    }

    public void setAuthor(UserRequestDto author) {
        this.author = author;
    }

    public UserRequestDto getPerformer() {
        return performer;
    }

    public void setPerformer(UserRequestDto performer) {
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
