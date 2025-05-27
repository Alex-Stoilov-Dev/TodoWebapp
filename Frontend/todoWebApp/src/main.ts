import { createApp } from "vue";
declare global {
  interface Window {
    editTodo: (title: string, description: string) => void;
    deleteTodo: (id: number) => void;
  }
}
import "./style.css";
import App from "./App.vue";

interface Task {
  id: number;
  task: string;
}

createApp(App).mount("#app");

let myTasks: string[] = [];

export async function fetchTasks(): Promise<Task[]> {
  const res = await fetch("/api/");
  return res.json();
}

export function addTask(task: string): Promise<{ id: number; task: string }> {
  return fetch("/api/", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ task }),
  })
    .then((res) => res.json())
    .then((data) => {
      console.log("Task added:", data);
      return data;
    });
}

export function deleteTask(id: number): void {
  fetch(`/api/${id}`, {
    method: "DELETE",
  })
    .then(() => {
      console.log("Task deleted:", id);
    })
    .catch((err) => console.error("Error deleting task:", err));
}

export function editTask(id: number, task: string): Promise<string[]> {
  return fetch(`/api/${id}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ task }),
  })
    .then((res) => res.json())
    .then((data: string[]) => {
      myTasks = data;
      console.log("Task edited:", id);
      return myTasks;
    })
    .catch((err) => {
      console.error("Error editing task:", err);
      return [];
    });
}
