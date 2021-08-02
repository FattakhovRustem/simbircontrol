package com.simbirsoft.simbircontrol.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "role")
    private String role;

    @Column(name = "surname")
    private String surname;

    @Column(name = "name")
    private String name;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "userLeader")
    private List<Project> projectsLeader;

    @OneToMany(mappedBy = "userAuthor")
    private List<Task> tasksAuthor;

    @OneToMany(mappedBy = "userPerformer")
    private List<Task> tasksPerformer;


    public User() {
    }

    public User(Integer id, String role, String surname, String name, String login, String password, List<Project> projectsLeader, List<Task> tasksAuthor, List<Task> tasksPerformer) {
        this.id = id;
        this.role = role;
        this.surname = surname;
        this.name = name;
        this.login = login;
        this.password = password;
        this.projectsLeader = projectsLeader;
        this.tasksAuthor = tasksAuthor;
        this.tasksPerformer = tasksPerformer;
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

    public List<Project> getProjectsLeader() {
        return projectsLeader;
    }

    public void setProjectsLeader(List<Project> projectsLeader) {
        this.projectsLeader = projectsLeader;
    }

    public List<Task> getTasksAuthor() {
        return tasksAuthor;
    }

    public void setTasksAuthor(List<Task> tasksAuthor) {
        this.tasksAuthor = tasksAuthor;
    }

    public List<Task> getTasksPerformer() {
        return tasksPerformer;
    }

    public void setTasksPerformer(List<Task> tasksPerformer) {
        this.tasksPerformer = tasksPerformer;
    }
}
