package com.simbirsoft.simbircontrol.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Пользователь")
public class UserRequestDto {

    @Schema(description = "ID пользователя")
    private Integer userId;

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

    public UserRequestDto() {
    }

    public UserRequestDto(Integer userId, String role, String surname, String name, String login, String password) {
        this.userId = userId;
        this.role = role;
        this.surname = surname;
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
