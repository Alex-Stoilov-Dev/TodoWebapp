package com.Todos.todo_list.service;

import com.Todos.todo_list.api.model.Todo;
import com.Todos.todo_list.api.repository.TodoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private List<Todo> todosList;

    public TodoService(TodoRepository todoRepository) {
        todosList = new ArrayList<>();
        this.todoRepository = todoRepository;
    }

    public List<Todo> getAllTodos() {
        return todosList;
    }


    public void addTodo(Todo todo){
        todosList.add(todo);
    }

    public void updateTodo(Integer id, Todo updatedTodo){
        Optional<Todo> existingTodoOptional = todoRepository.findById(id);

        if(existingTodoOptional.isPresent()){
            Todo existingTodo = existingTodoOptional.get();
            existingTodo.setTitle(updatedTodo.getTitle());
            existingTodo.setTodo(updatedTodo.getTodo());
        }
        else {
            throw new EntityNotFoundException("Todo with this ID ->" + id + "<- not found.");
        }
    }

    public void deleteTodo(Integer id){
        Optional<Todo> exisitingTodoOptional = todoRepository.findById(id);
        if(exisitingTodoOptional.isPresent()){
            todoRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Todo with this ID ->" + id + "<- not found.");
        }
    }

}
