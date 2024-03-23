package br.com.taskManager.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Table(name = "task")
@Entity (name = "task")
@Getter
@Setter
public class Task implements Comparable<Task>{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column (name = "title")
    @NotBlank
    private String title;
    @Column (name = "description")
    private String description;
    @Column (name = "expiration")
    @NotBlank
    private String expiration;

    private Date expirationDate;
    public Task(String title, String description, String expiration) {
        this.title = title;
        this.description = description;
        this.expiration = expiration;
    }

    public Task() {

    }

    @Override
    public int compareTo(Task task) {
        return getExpirationDate().compareTo(task.getExpirationDate());
    }
}
