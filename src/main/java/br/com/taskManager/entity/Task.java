package br.com.taskManager.entity;

import jakarta.persistence.*;

import java.util.Date;

@Table(name = "task")
@Entity (name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column (name = "title")
    private String title;
    @Column (name = "description")
    private String description;
    @Column (name = "expiration")
    private String expiration;
    public Task(String title, String description, String expiration) {
        this.title = title;
        this.description = description;
        this.expiration = expiration;
    }

    public Task() {

    }
}
