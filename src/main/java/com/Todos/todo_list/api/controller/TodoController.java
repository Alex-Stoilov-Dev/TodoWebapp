package com.Todos.todo_list.api.controller;

import com.Todos.todo_list.api.model.Todo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.Todos.todo_list.service.TodoService;

@RestController
@RequestMapping("")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) { this.todoService = todoService; }

    @GetMapping("/api/todo")
    public List<Todo> getTodos(){
        return todoService.getAllTodos();
    }

    @PostMapping("/api/todo")
    public void addTodo(@RequestBody Todo todo){
        todoService.addTodo(todo);
    }
    @PutMapping("/api/todo/{id}")
    public void updateTodo(@RequestBody Todo todo){
        todoService.updateTodo(todo.getId(),todo);
    }
    @DeleteMapping("/api/todo/{id}")
    public void deleteTodo(@PathVariable int id){
        todoService.deleteTodo(id);
    }

}
