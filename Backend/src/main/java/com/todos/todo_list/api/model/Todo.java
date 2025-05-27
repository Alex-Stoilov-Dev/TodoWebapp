package com.todos.todo_list.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tasks_table")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    private String task;

    public Todo() {}

    public Todo(String task) {
        this.task = task;
    }

    public Integer getId() { return id; }
    public String getTask() { return task; }

    public void setTask(String task) { this.task = task; }
}
