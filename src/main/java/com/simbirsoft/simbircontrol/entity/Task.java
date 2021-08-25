package com.simbirsoft.simbircontrol.entity;

import com.simbirsoft.simbircontrol.enums.State;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;


@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private State state;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "release_id", nullable = false)
    private Release release;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id_author", nullable = false)
    private Usr usrAuthor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id_performer", nullable = false)
    private Usr usrPerformer;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project projectTask;

    public Task() {
    }

    public Task(Integer id, State state, String description, Release release, Usr usrAuthor, Usr usrPerformer, String name, Project projectTask) {
        this.id = id;
        this.state = state;
        this.description = description;
        this.release = release;
        this.usrAuthor = usrAuthor;
        this.usrPerformer = usrPerformer;
        this.name = name;
        this.projectTask = projectTask;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Release getRelease() {
        return release;
    }

    public void setRelease(Release release) {
        this.release = release;
    }

    public Usr getUsrAuthor() {
        return usrAuthor;
    }

    public void setUsrAuthor(Usr usrAuthor) {
        this.usrAuthor = usrAuthor;
    }

    public Usr getUsrPerformer() {
        return usrPerformer;
    }

    public void setUsrPerformer(Usr usrPerformer) {
        this.usrPerformer = usrPerformer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Project getProjectTask() {
        return projectTask;
    }

    public void setProjectTask(Project projectTask) {
        this.projectTask = projectTask;
    }

    @Override
    public boolean equals(Object obj) {
        Task task = (Task) obj;
        return this.id.equals(task.id) && this.name.equals(task.name) && this.projectTask.getId().equals(task.getProjectTask().getId());
    }
}
