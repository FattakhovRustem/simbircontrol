package com.simbirsoft.simbircontrol.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;


@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id_leader", nullable = false)
    private User userLeader;

    @Column(name = "state")
    private String state;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "projectRelease")
    private List<Release> releases;

    @OneToMany(mappedBy = "projectTask")
    private List<Task> tasks;

    public Project() {
    }

    public Project(Integer id, User userLeader, String state, String name, Client client, String description, List<Release> releases, List<Task> tasks) {
        this.id = id;
        this.userLeader = userLeader;
        this.state = state;
        this.name = name;
        this.client = client;
        this.description = description;
        this.releases = releases;
        this.tasks = tasks;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUserLeader() {
        return userLeader;
    }

    public void setUserLeader(User userLeader) {
        this.userLeader = userLeader;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Release> getReleases() {
        return releases;
    }

    public void setReleases(List<Release> releases) {
        this.releases = releases;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
