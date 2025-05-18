package com.Todos.todo_list.api.model;

import jakarta.persistence.*;

@Entity

public class Todo {

    @id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    private String title;
    private String todo;

    // Constructors
    public Todo(){}


    public Todo(int id, String title, String todo) {
        this.id = id;
        this.title = title;
        this.todo = todo;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getTodo() { return todo; }

    public void setId(int id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setTodo(String todo) {this.todo = todo;}


}
