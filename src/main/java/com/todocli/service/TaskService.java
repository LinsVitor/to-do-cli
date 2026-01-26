package com.todocli.service;

import com.todocli.model.Status;
import com.todocli.model.Task;
import com.todocli.repository.SQLiteRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class TaskService {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    private static SQLiteRepository repository;

    public TaskService() {
        repository = new SQLiteRepository();
    }

    public int create(String title, String description) {
        if (title.isEmpty()) {
            throw new ServiceException("Title can't be empty. Please enter a valid title.");
        }
        if (description.isEmpty()) {
            throw new ServiceException("Description can't be empty. Please enter a valid description.");
        }
        Task task = new Task(title, description);
        return repository.insert(task);
    }

    public void delete(int id) {
        if (id < 0) {
            throw new ServiceException("The task ID can't be less than zero. Please enter a valid number.");
        }
        repository.delete(id);
    }

    public void update(int id, String title, String description) {
        if (id < 0) {
            throw new ServiceException("The task ID can't be less than zero. Please enter a valid number.");
        }
        if (title.isEmpty()) {
            throw new ServiceException("Title can't be empty. Please enter a valid title.");
        }
        if (description.isEmpty()) {
            throw new ServiceException("Description can't be empty. Please enter a valid description.");
        }
        Task task = repository.findById(id);
        task.setTitle(title);
        task.setDescription(description);
        task.setUpdatedAt(LocalDateTime.parse(LocalDateTime.now().format(formatter)));
        repository.update(id, task);
    }

    public void mark(int id, String status) {
        if (id < 0) {
            throw new ServiceException("The task ID can't be less than zero. Please enter a valid number.");
        }
        if (status.isEmpty()) {
            throw new ServiceException("Status can't be empty. Please enter a valid status (todo, in progress or done).");
        }
        String formatedStatus = formatStatus(status);
        if (noneMatchStatus(formatedStatus)) {
            throw new ServiceException("None match for this status. Valid status (todo, in progress or done)");
        }
        Task task = repository.findById(id);
        task.setStatus(Status.valueOf(formatedStatus));
        task.setUpdatedAt(LocalDateTime.parse(LocalDateTime.now().format(formatter)));
        repository.update(id, task);
    }

    public Task find(int id) {
        if (id < 0) {
            throw new ServiceException("The task ID can't be less than zero. Please enter a valid number.");
        }
        return repository.findById(id);
    }

    public List<Task> findAll() {
        List<Task> tasks = repository.findAll();
        if (tasks.isEmpty()) {
            throw new ServiceException("No tasks found.");
        }
        return tasks;
    }

    public List<Task> findByStatus(String status) {
        if (status.isEmpty()) {
            throw new ServiceException("Status can't be empty. Please enter a valid status (todo, in progress or done).");
        }
        String formatedStatus = formatStatus(status);
        if (noneMatchStatus(formatedStatus)) {
            throw new ServiceException("None match for this status. Valid status (todo, in progress or done)");
        }
        List<Task> tasks = repository.findByStatus(Status.valueOf(formatedStatus));
        if (tasks.isEmpty()) {
            throw new ServiceException("No tasks found.");
        }
        return tasks;
    }

    public List<Task> searchByTitle(String title) {
        if (title.isEmpty()) {
            throw new ServiceException("Search field can't be empty.");
        }
        List<Task> tasks = repository.searchByTitle(title);
        if (tasks.isEmpty()) {
            throw new ServiceException("No tasks found.");
        }
        return tasks;
    }

    private String formatStatus(String status) {
        return status.toUpperCase().trim().equals("INPROGRESS") ? status.toUpperCase().trim() : status.toUpperCase().replace(" ", "_").trim();
    }

    private boolean noneMatchStatus(String status) {
        return Arrays.stream(Status.values()).noneMatch(x -> x.name().equals(status));
    }
}
