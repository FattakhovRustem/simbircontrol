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
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "client")
    private List<Project> projects;

    @Column(name = "number")
    private Integer number;

    public Client() {
    }

    public Client(Integer id, String name, List<Project> projects, Integer number) {
        this.id = id;
        this.name = name;
        this.projects = projects;
        this.number = number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer numberr) {
        this.number = numberr;
    }

}
