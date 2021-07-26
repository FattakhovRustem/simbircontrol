package com.simbirsoft.simbircontrol.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;


@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "state")
    private String state;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "release_id", nullable = false)
    private Release releaseId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id_author", nullable = false)
    private User userIdAuthor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id_performer", nullable = false)
    private User userIdPerformer;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project projectId;

    @Column(name = "description")
    private String description;

    public Task() {
    }

}
