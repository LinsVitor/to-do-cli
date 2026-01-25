package com.todocli.service;

import com.todocli.model.Status;
import com.todocli.model.Task;
import com.todocli.repository.SQLiteRepository;
import com.todocli.util.Color;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TaskService {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    private static SQLiteRepository repository;

    public TaskService() {
        repository = new SQLiteRepository();
    }

    public int create(String title, String description) {
        if (title.isEmpty()) {
            throw new ServiceException(Color.getRED()
                    + "Title can't be empty. Please enter a valid title."
                    + Color.getRESET()
            );
        }
        if (description.isEmpty()) {
            throw new ServiceException(Color.getRED()
                    + "Description can't be empty. Please enter a valid description."
                    + Color.getRESET()
            );
        }
        Task task = new Task(title, description);
        return repository.insert(task);
    }

    public void delete(int id) {
        if (id < 0) {
            throw new ServiceException(Color.getRED()
                    + "The task ID can't be less than zero. Please enter a valid number."
                    + Color.getRESET()
            );
        }
        repository.delete(id);
    }

    public void update(int id, String title, String description) {
        if (id < 0) {
            throw new ServiceException(Color.getRED()
                    + "The task ID can't be less than zero. Please enter a valid number."
                    + Color.getRESET()
            );
        }
        if (title.isEmpty()) {
            throw new ServiceException(Color.getRED()
                    + "Title can't be empty. Please enter a valid title."
                    + Color.getRESET()
            );
        }
        if (description.isEmpty()) {
            throw new ServiceException(Color.getRED()
                    + "Description can't be empty. Please enter a valid description."
                    + Color.getRESET()
            );
        }
        Task task = repository.findById(id);
        task.setTitle(title);
        task.setDescription(description);
        task.setUpdatedAt(LocalDateTime.parse(LocalDateTime.now().format(formatter)));
        repository.update(id, task);
    }

    public void mark(int id, String status) {
        if (id < 0) {
            throw new ServiceException(Color.getRED()
                    + "The task ID can't be less than zero. Please enter a valid number."
                    + Color.getRESET()
            );
        }
        if (status.isEmpty()) {
            throw new ServiceException(Color.getRED()
                    + "Status can't be empty. Please enter a valid status (todo, in progress or done)."
                    + Color.getRESET()
            );
        }
        Task task = repository.findById(id);
        task.setStatus(Status.valueOf(status.toUpperCase()));
        task.setUpdatedAt(LocalDateTime.parse(LocalDateTime.now().format(formatter)));
        repository.update(id, task);
    }

    public Task find(int id) {
        if (id < 0) {
            throw new ServiceException(Color.getRED()
                    + "The task ID can't be less than zero. Please enter a valid number."
                    + Color.getRESET()
            );
        }
        return repository.findById(id);
    }

    public List<Task> findAll() {
        List<Task> tasks = repository.findAll();
        if (tasks.isEmpty()) {
            throw new ServiceException(Color.getRED()
                    + "No tasks found."
                    + Color.getRESET()
            );
        }
        return tasks;
    }

    public List<Task> findByStatus(String status) {
        if (status.isEmpty()) {
            throw new ServiceException(Color.getRED()
                    + "Status can't be empty. Please enter a valid status (todo, in progress or done)."
                    + Color.getRESET()
            );
        }
        List<Task> tasks = repository.findByStatus(Status.valueOf(status.toUpperCase()));
        if (tasks.isEmpty()) {
            throw new ServiceException(Color.getRED()
                    + "No tasks found."
                    + Color.getRESET()
            );
        }
        return tasks;
    }

    public List<Task> searchByTitle(String title) {
        if (title.isEmpty()) {
            throw new ServiceException(Color.getRED()
                    + "Search field can't be empty."
                    + Color.getRESET()
            );
        }
        List<Task> tasks = repository.searchByTitle(title);
        if (tasks.isEmpty()) {
            throw new ServiceException(Color.getRED()
                    + "No tasks found."
                    + Color.getRESET()
            );
        }
        return tasks;
    }
}
