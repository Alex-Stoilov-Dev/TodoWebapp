package com.Todos.todo_list.service;

import com.Todos.todo_list.api.model.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TodoService {

    private List<Todo> todosList;

    public TodoService() {
        todosList = new ArrayList<>();

        Todo todo1 = new Todo(1,"Walk Dog","Walk the dog around the block");
        Todo todo2 = new Todo(2,"Buy Groceries","Buy groceries: milk, eggs, bread");
        Todo todo3 = new Todo(3,"Take out trash","Take out the trash before work");

        todosList.addAll(Arrays.asList(todo1, todo2, todo3));


    }

    public List<Todo> getAllTodos() {
        return todosList;
    }
    public void addTodo(Todo todo){
        todosList.add(todo);
    }

    public void updateTodo(int id, Todo updatedTodo){
        for (Todo todo : todosList) {
            if(todo.getId() == id){
                todo.setTitle(updatedTodo.getTitle());
                todo.setTodo(updatedTodo.getTodo());
                break;
            }
        }
    }

    public void deleteTodo(int id){
        for (Todo todo : todosList) {
            if(todo.getId() == id){
                todosList.remove(todo);
                break;
            }
        }
    }

}
