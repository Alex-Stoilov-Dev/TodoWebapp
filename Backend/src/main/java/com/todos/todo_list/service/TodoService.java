package com.todos.todo_list.api.service;

import com.todos.todo_list.api.model.Todo;
import com.todos.todo_list.api.repository.TodoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private List<Todo> tasksList;

    public TodoService(TodoRepository todoRepository) {
        tasksList = new ArrayList<>();
        this.todoRepository = todoRepository;
    }

    public List<Todo> getAllTasks() {
        return tasksList;
    }


    public void addTask(Todo task){
        tasksList.add(task);
        todoRepository.save(task);
    }

    public void updateTask(Integer id, Todo updatedTask){
        Optional<Todo> existingTodoOptional = todoRepository.findById(id);

        if(existingTodoOptional.isPresent()){
            Todo existingTodo = existingTodoOptional.get();
            existingTodo.setTask(updatedTask.getTask());
            todoRepository.save(existingTodo);
        }
        else {
            throw new EntityNotFoundException("Todo with this ID ->" + id + "<- not found.");
        }
    }

    public void deleteTask(Integer id){
        Optional<Todo> exisitingTodoOptional = todoRepository.findById(id);
        if(exisitingTodoOptional.isPresent()){
            todoRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Todo with this ID ->" + id + "<- not found.");
        }
    }

}
