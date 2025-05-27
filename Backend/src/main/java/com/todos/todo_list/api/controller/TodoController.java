package com.todos.todo_list.api.controller;

import com.todos.todo_list.api.model.Todo;
import com.todos.todo_list.api.repository.TodoRepository;
import com.todos.todo_list.api.model.ApiResponse;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Sort;

import java.util.List;

import com.todos.todo_list.api.service.TodoService;

@RestController
@RequestMapping("/api")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @Autowired
    private TodoRepository todoRepository;

    @PostMapping("/")
    public ResponseEntity<Todo> insertTask(@RequestBody Todo task) {
        Todo savedTask = todoRepository.save(task);
        return ResponseEntity.ok(savedTask);
    }

    @GetMapping("/")
    public List<Todo> getAllTasks() {
        return todoRepository.findAll(Sort.by("id"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateTask(@PathVariable Integer id, @RequestBody Todo updatedTask) {
        try {
            todoService.updateTask(id, updatedTask);

            ApiResponse apiResponse = new ApiResponse();

            apiResponse.setMessage("Task Updated successful");

            return ResponseEntity.ok().body(apiResponse);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteTask(@PathVariable Integer id) {
        try {
            todoService.deleteTask(id);
            ApiResponse apiResponse = new ApiResponse();

            apiResponse.setMessage("Task deleted successfully");

            return ResponseEntity.ok().body(apiResponse);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
        }
    }

}
