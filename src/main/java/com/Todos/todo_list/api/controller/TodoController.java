package com.Todos.todo_list.api.controller;

import com.Todos.todo_list.api.model.Todo;
import com.Todos.todo_list.api.repository.TodoRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.Todos.todo_list.service.TodoService;

@RestController
@RequestMapping("/api")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) { this.todoService = todoService; }

    @Autowired
    private TodoRepository todoRepository;



    @PostMapping("/save")
    public Todo insertTodo(@RequestBody Todo todo) {
        return todoRepository.save(todo);
    }

    @GetMapping("/getTodos")
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }


    @GetMapping("/todo")
    public List<Todo> getTodos(){
        return todoService.getAllTodos();
    }

    @PostMapping("/todo")
    public void addTodo(@RequestBody Todo todo){
        todoService.addTodo(todo);
    }

    @PutMapping("/todo/{id}")
    public ResponseEntity<String> updateTodo(@PathVariable Integer id, @RequestBody Todo updateTodo){
        try{
            todoService.updateTodo(id, updateTodo);
            return ResponseEntity.ok().body("Todo Updated successful");
        } catch(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/todo/delete_{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Integer id){
        try{
            todoService.deleteTodo(id);
            return  ResponseEntity.ok().body("Todo Deleted successful");
        }catch(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
