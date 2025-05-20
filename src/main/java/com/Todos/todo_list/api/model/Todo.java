package com.Todos.todo_list.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "todoTable")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String todo;

    public Todo() {}

    public Todo(String title, String todo) {
        this.title = title;
        this.todo = todo;
    }

    public Integer getId() { return id; }

    public String getTitle() { return title; }

    public String getTodo() { return todo; }

    public void setId(Integer id) { this.id = id; }

    public void setTitle(String title) { this.title = title; }

    public void setTodo(String todo) { this.todo = todo; }
}
