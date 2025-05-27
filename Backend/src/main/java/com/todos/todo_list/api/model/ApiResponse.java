package com.todos.todo_list.api.model;

public class ApiResponse {

    //TODO: Nqkvi tupi shibani responsove shtoto inache bugva fornt enda!!!!!!
    private String message;

    public ApiResponse() {
    }

    public ApiResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
