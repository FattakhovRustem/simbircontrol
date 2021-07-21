package com.simbirsoft.simbircontrol.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

@Schema(description = "Релиз")
public class ReleaseResponseDto {

    @Schema(description = "ID релиза")
    private Integer id;

    //@Schema(description = "ID проекта")
    //private Integer projectId;

    @Schema(description = "Версия релиза")
    private String version;

    @Schema(description = "Дата начала релиза")
    private Date dateStart;

    @Schema(description = "Дата конца релиза")
    private Date dateEnd;

    public ReleaseResponseDto() {
    }

    public ReleaseResponseDto(Integer id, String version, Date dateStart, Date dateEnd) {
        this.id = id;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }
}
