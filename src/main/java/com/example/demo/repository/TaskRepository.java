package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.entity.Task;

// Model type and id type
public interface TaskRepository extends JpaRepository<Task, Long> {
    public List<Task> findByuserid(Long userId);
    public Task findByIdAndUserid(Long taskId, Long userId);
}