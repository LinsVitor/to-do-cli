package com.todocli.service;

import com.todocli.exception.InvalidArgumentException;
import com.todocli.exception.ServiceException;
import com.todocli.model.Status;
import com.todocli.model.Task;
import com.todocli.repository.SQLiteRepository;
import com.todocli.util.ErrorMessage;

import java.time.LocalDateTime;
import java.util.List;

public class TaskService {

    private final SQLiteRepository repository;

    public TaskService(SQLiteRepository repository) {
        this.repository = repository;
    }

    public int create(String title, String description) {
        verifyTitle(title);
        Task task = new Task(title, description);
        return repository.insert(task);
    }

    public void delete(int id) {
        verifyId(id);
        repository.delete(id);
    }

    public void update(int id, String title, String description) {
        verifyId(id);
        verifyTitle(title);
        Task task = repository.findById(id);
        task.setTitle(title);
        task.setDescription(description);
        task.setUpdatedAt(LocalDateTime.now());
        repository.update(id, task);
    }

    public void mark(int id, String status) {
        verifyId(id);
        Task task = repository.findById(id);
        task.setStatus(Status.fromString(status));
        task.setUpdatedAt(LocalDateTime.now());
        repository.update(id, task);
    }

    public Task find(int id) {
        verifyId(id);
        return repository.findById(id);
    }

    public List<Task> findAll() {
        List<Task> tasks = repository.findAll();
        if (tasks.isEmpty()) {
            throw new ServiceException(ErrorMessage.EMPTY_TASK_LIST.getMessage());
        }
        return tasks;
    }

    public List<Task> findByStatus(String status) {
        List<Task> tasks = repository.findByStatus(Status.fromString(status));
        if (tasks.isEmpty()) {
            throw new ServiceException(ErrorMessage.EMPTY_TASK_LIST.getMessage());
        }
        return tasks;
    }

    public List<Task> searchOnTitle(String keyword) {
        List<Task> tasks = repository.searchByTitle(keyword);
        if (tasks.isEmpty()) {
            throw new ServiceException(ErrorMessage.EMPTY_TASK_LIST.getMessage());
        }
        return tasks;
    }

    private void verifyTitle(String title) {
        if (title.trim().length() > 28) {
            throw new InvalidArgumentException(ErrorMessage.LARGE_TITLE.getMessage());
        }
    }

    private void verifyId(int id) {
        if (id < 0) {
            throw new InvalidArgumentException(ErrorMessage.NEGATIVE_ID.getMessage());
        }
    }
}
