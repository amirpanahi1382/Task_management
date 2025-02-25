package com.example.taskmanagement.controller;

import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.model.TaskStatus;
import com.example.taskmanagement.repository.TaskRepository;
import com.example.taskmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;


    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }


        @PutMapping("/{id}/assign/{userId}")
    public Task assignTask(@PathVariable Long id, @PathVariable Long userId) {
        Task task = taskRepository.findById(id).orElseThrow();
        task.setAssignedUser(userRepository.findById(userId).orElseThrow());
        return taskRepository.save(task);
    }


    @PutMapping("/{id}/status")
    public Task updateStatus(@PathVariable Long id, @RequestParam TaskStatus status) {
        Task task = taskRepository.findById(id).orElseThrow();
        task.setStatus(status);
        return taskRepository.save(task);
    }
}
