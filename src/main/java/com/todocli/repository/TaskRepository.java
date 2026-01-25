package com.todocli.repository;

import com.todocli.model.Status;
import com.todocli.model.Task;

import java.util.List;

public interface TaskRepository {
    int insert(Task task);
    void delete(int id);
    void deleteAll();
    void update(int id, Task task);
    Task findById(int id);
    List<Task> searchByTitle(String title);
    List<Task> findAll();
    List<Task> findByStatus(Status status);
}
