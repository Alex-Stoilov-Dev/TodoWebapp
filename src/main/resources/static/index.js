document.addEventListener('DOMContentLoaded', () => {
    const todoList = document.getElementById('todos');
    const addForm = document.getElementById('add-todo-form');
    const updateForm = document.getElementById('update-todo-form');
    const updateTodoSection = document.getElementById('update-todo-form');  // <-- Fix 3

    fetchTodos();

    addForm.addEventListener('submit', (e) => {
        e.preventDefault();

        const title = document.getElementById('title').value;
        const description = document.getElementById('description').value;

        const newTodo = { title, todo: description };  // <-- Your backend expects `todo`, not `description`

        fetch('/api/todo', {  // <-- Always add leading `/` in fetch URLs
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(newTodo)
        })
            //.then(response => response.json())  // <-- Fix 4
            .then(() => {
                fetchTodos();
            })
            .catch(error => console.error('Error:', error));
    });

    updateForm.addEventListener('submit', (e) => {
        e.preventDefault();

        const id = document.getElementById('update-id').value;
        const title = document.getElementById('update-title').value;
        const description = document.getElementById('update-description').value;  // <-- Fix 2

        const updatedTodo = { id: parseInt(id), title, todo: description };

        fetch(`/api/todo/${id}`, {   // <-- Fix 1 — correct template literal
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(updatedTodo)
        })
            //.then(response => response.json())
            .then(() => {
                fetchTodos();
                updateTodoSection.style.display = 'none';
            })
            .catch(error => console.error('Error:', error));
    });

    function fetchTodos() {
        fetch('/api/todo')
            .then(response => response.json())
            .then(todos => {
                todoList.innerHTML = '';
                todos.forEach(todo => {
                    const li = document.createElement('li');
                    li.innerHTML = `${todo.title} - ${todo.todo}
                    <button onclick="editTodo(${todo.id}, '${todo.title}', '${todo.todo}')">Edit</button>
                    <button onclick="deleteTodo(${todo.id})">Delete</button>`;
                    todoList.appendChild(li);
                });
            });
    }

    // Move BOTH of these INSIDE DOMContentLoaded — Fix 5
    window.editTodo = function (id, title, description) {
        document.getElementById('update-id').value = id;
        document.getElementById('update-title').value = title;
        document.getElementById('update-description').value = description;
        updateTodoSection.style.display = 'block';
    };

    window.deleteTodo = function (id) {
        fetch(`/api/todo/${id}`, {   // <-- Fix 1 here too
            method: 'DELETE',
        })
            .then(() => fetchTodos())
            .catch(error => console.error('Error:', error));
    };
});
