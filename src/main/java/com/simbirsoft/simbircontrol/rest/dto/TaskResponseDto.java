package com.simbirsoft.simbircontrol.rest.dto;

import com.simbirsoft.simbircontrol.enums.State;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Задача")
public class TaskResponseDto {

    @Schema(description = "ID задачи")
    private Integer id;

    @Schema(description = "Статус")
    private State state;

    @Schema(description = "ID релиза")
    private Integer releaseId;

    @Schema(description = "ID автора")
    private Integer idAuthor;

    @Schema(description = "ID исполнителя")
    private Integer idPerformer;

    @Schema(description = "Наименование задачи")
    private String name;

    @Schema(description = "ID проекта")
    private Integer projectId;

    @Schema(description = "Описание задачи")
    private String description;

    public TaskResponseDto() {
    }

    public TaskResponseDto(Integer id, State state, Integer releaseId, Integer idAuthor, Integer idPerformer, String name, Integer projectId, String description) {
        this.id = id;
        this.state = state;
        this.releaseId = releaseId;
        this.idAuthor = idAuthor;
        this.idPerformer = idPerformer;
        this.name = name;
        this.projectId = projectId;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Integer getReleaseId() {
        return releaseId;
    }

    public void setReleaseId(Integer releaseId) {
        this.releaseId = releaseId;
    }

    public Integer getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(Integer idAuthor) {
        this.idAuthor = idAuthor;
    }

    public Integer getIdPerformer() {
        return idPerformer;
    }

    public void setIdPerformer(Integer idPerformer) {
        this.idPerformer = idPerformer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
