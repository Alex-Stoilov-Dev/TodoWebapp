<template>
  <div class="container">
    <!-- Titul na stranicata mislq -->
    <header>
      <h1>Todo List</h1>
    </header>

    <!-- Spisuk sus Zadachite -->

    <div id="tasks">
      <ul id="tasks-list">
        <!-- Tuk shte izliza spisukut sus zadachi. Generira se dinamichno -->
        <li v-for="(task, index) in BackendTasks" :key="task.id">
          <strong>{{ index + 1 }}</strong> - {{ task.task }}
          <button @click="handleEdit(task.id)">Edit</button>
          <button @click="handleDelete(task.id)">Delete</button>
        </li>
      </ul>
    </div>
    <!-- Dobavqne na Zadachki -->
    <div class="general-forms">
      <section id="add-todo">
        <h2>Add New Todo</h2>
        <form id="add-todo-form">
          <label for="addForm">Task</label>
          <textarea
            id="addForm"
            name="todo"
            type="text"
            placeholder="Enter Task"
            rows="4"
            cols="40"
            required
          ></textarea>
          <button type="submit" id="addBtn">Add Todo</button>
        </form>
      </section>
      <!-- Aktualizirane na spisuka sus zadachi-->
      <section id="update-todo">
        <h2>Update/Edit Todo</h2>
        <form id="update-todo-form">
          <label for="update-Task">Task</label>
          <textarea
            id="update-Task"
            name="task"
            type="text"
            placeholder="Enter Task"
            rows="4"
            cols="40"
            required
          ></textarea>
          <button type="submit" id="updateBtn">Update Todo</button>
        </form>
      </section>
    </div>
  </div>
</template>

<style scoped>
.general-forms {
  display: flex;
  flex-direction: row;
  gap: 20px;
}

#tasks {
  flex: 1;
  display: flex;
  margin: 20px;
  padding: 10px;
  text-align: left;
}

form#add-todo-form,
form#update-todo-form {
  display: flex;
  flex-direction: column;
  gap: 10px;
  max-width: 300px;
}

form#add-todo-form input,
form#update-todo-form input {
  padding: 5px;
  font-size: 16px;
}

h1 {
  font-family: "Arial", sans-serif;
  font-style: oblique;
}

label {
  font-weight: bold;
  font-family: "Arial", sans-serif;
}
</style>

<script lang="ts">
import { onMounted, ref } from "vue";
import { fetchTasks, addTask, editTask, deleteTask } from "../main";

export default {
  setup() {
    interface Task {
      id: number;
      task: string;
    }

    const BackendTasks = ref<Task[]>([]);

    onMounted(async () => {
      const tasks = await fetchTasks();
      BackendTasks.value = tasks;
      JSON.stringify(BackendTasks.value);
      console.log("Tasks fetched from backend:", BackendTasks.value);
    });

    function handleDelete(id: number) {
      deleteTask(id);
      BackendTasks.value = BackendTasks.value.filter((task) => task.id !== id);
    }

    function handleEdit(id: number) {
      const taskToEdit = BackendTasks.value.find((task) => task.id === id);
      if (taskToEdit) {
        const updateTask = document.getElementById(
          "update-Task",
        ) as HTMLInputElement;
        updateTask.value = taskToEdit.task;

        const updateBtn = document.getElementById(
          "updateBtn",
        ) as HTMLButtonElement;

        updateBtn.onclick = async () => {
          const updatedTask = updateTask.value;
          if (updatedTask !== "") {
            try {
              await editTask(id, updatedTask);
              const index = BackendTasks.value.findIndex(
                (task) => task.id === id,
              );
              if (index !== -1) {
                BackendTasks.value[index].task = updatedTask;
              }
            } catch (error) {
              console.log(error);
            }
          } else {
            alert("Please enter a valid task.");
          }
        };
      }
    }

    window.addEventListener("DOMContentLoaded", () => {
      const addBtn = document.getElementById("addBtn") as HTMLButtonElement;
      const addForm = document.getElementById("addForm") as HTMLTextAreaElement;

      addBtn.onclick = async (event) => {
        event.preventDefault();

        if (addForm.value.trim() === "") {
          alert("Please enter a valid task.");
          return;
        }

        console.log("Add button clicked");

        const taskToAdd = await addTask(addForm.value);
        BackendTasks.value.push(taskToAdd);

        console.log("New task added:", taskToAdd);
        alert("Task added successfully!");
        addForm.value = ""; // clear input after adding
      };
    });

    return {
      BackendTasks,
      handleDelete,
      handleEdit,
    };
  },
};
</script>
