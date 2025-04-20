package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.entity.Task;

// Model type and id type
public interface TaskRepository extends JpaRepository<Task, Long> {
    public List<Task> findByuserId(Long userId);
    public Task findByIdAndUserId(Long taskId, Long userId);
}