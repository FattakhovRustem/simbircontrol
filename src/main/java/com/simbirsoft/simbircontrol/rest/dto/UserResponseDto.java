package com.simbirsoft.simbircontrol.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Пользователь")
public class UserResponseDto {

    @Schema(description = "ID пользователя")
    private Integer id;

    @Schema(description = "Роль")
    private String role;

    @Schema(description = "Фамилия")
    private String surname;

    @Schema(description = "Имя")
    private String name;

    @Schema(description = "Логин")
    private String login;

    @Schema(description = "Пароль")
    private String password;

    public UserResponseDto() {
    }

    public UserResponseDto(Integer id, String role, String surname, String name, String login, String password) {
        this.id = id;
        this.role = role;
        this.surname = surname;
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
