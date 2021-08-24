package com.simbirsoft.simbircontrol.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Релиз")
public class ReleaseRequestDto {

    @Schema(description = "ID релиза")
    private Integer id;

    @Schema(description = "ID проекта")
    private Integer projectId;

    @Schema(description = "Версия релиза")
    private String version;

    @Schema(description = "Дата начала релиза")
    private LocalDateTime dateStart;

    @Schema(description = "Дата конца релиза")
    private LocalDateTime dateEnd;

    public ReleaseRequestDto() {
    }

    public ReleaseRequestDto(Integer id, Integer projectId, String version, LocalDateTime dateStart, LocalDateTime dateEnd) {
        this.id = id;
        this.projectId = projectId;
        this.version = version;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public LocalDateTime getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDateTime dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDateTime getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDateTime dateEnd) {
        this.dateEnd = dateEnd;
    }

    @Override
    public String toString() {
        return "id = " + id +
                ", projectId = " + projectId +
                ", version = " + version +
                ", dateStart = " + dateStart.toString() +
                ", dateEnd = " + dateEnd.toString();
    }
}
