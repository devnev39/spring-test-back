package com.example.demo.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exceptions.TaskNotFoundException;
import com.example.demo.models.common.UserPrincipal;
import com.example.demo.models.entity.Task;
import com.example.demo.repository.TaskRepository;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    
   private final TaskRepository repository;

   TaskController(TaskRepository repository) {
    this.repository = repository;
   }

   @GetMapping("")
    ResponseEntity<List<Task>> all() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal u = (UserPrincipal) auth.getPrincipal();
        return new ResponseEntity<>(repository.findByuserId(u.getUser().getId()), HttpStatus.OK);
    }

    // @GetMapping("/stat")
    // Map<String, String> getStats() {
    //     Map<String, String> mp = new HashMap<>();
    // }

   @PostMapping("")
    Task newTask(@RequestBody Task task) {
        // throw new NotImplementedException("Not implemented!");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal u = (UserPrincipal) auth.getPrincipal();
        task.setUserId(u.getUser().getId());
        return repository.save(task);
   }

   @GetMapping("/{id}")
   Task one(@PathVariable Long id) {
    return repository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
   }

   @PatchMapping("/{id}")
   Task replaceTask(@RequestBody Task newTask, @PathVariable Long id, HttpServletRequest request) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    UserPrincipal u = (UserPrincipal) auth.getPrincipal();
    Task task =  repository.findByIdAndUserId(id, u.getUser().getId());
    if (task == null) {
        throw new TaskNotFoundException(id);
    } else {
        if (newTask.getName() != null) task.setName(newTask.getName());
        if (newTask.getCompleted() != null) task.setCompleted(newTask.getCompleted());
        if (newTask.getDescription() != null) task.setDescription(newTask.getDescription());
        task.setUpdatedAtDefault();
        return repository.save(task);
    }
   }

   @DeleteMapping("/{id}")
   void deleteTask(@PathVariable Long id) {
    repository.deleteById(id);
   }
}
