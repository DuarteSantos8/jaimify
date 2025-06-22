package com.example.jamify.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.jamify.model.Task;
import com.example.jamify.repository.TaskRepository;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class TaskRestController {

    private static final Logger log = LoggerFactory.getLogger(TaskRestController.class);

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getAllTasks() {
        log.info("GET /api/tasks - Retrieving all tasks");
        List<Task> tasks = (List<Task>) taskRepository.findAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(tasks);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        log.info("GET /api/tasks/{} - Retrieving task by ID", id);
        Optional<Task> task = taskRepository.findById(id);

        if (task.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(task.get());
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @PostMapping("/tasks")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        log.info("POST /api/tasks - Creating new task: {}", task.getDescription());
        task.setId(null);
        Task savedTask = taskRepository.save(task);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(savedTask);
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        log.info("PUT /api/tasks/{} - Updating task", id);

        Optional<Task> optionalTask = taskRepository.findById(id);

        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setDescription(taskDetails.getDescription());
            task.setCompleted(taskDetails.isCompleted());

            Task updatedTask = taskRepository.save(task);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(updatedTask);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        log.info("DELETE /api/tasks/{} - Deleting task", id);

        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }
}