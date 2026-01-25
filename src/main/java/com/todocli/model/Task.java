package com.todocli.model;

import com.todocli.util.Color;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Task {

    private Integer taskId;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Status status;

    public Task() {
    }

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        status = Status.TODO;
        createdAt = LocalDateTime.parse(LocalDateTime.now()
                .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        );
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        if (updatedAt != null) {
            return Color.getBLUE() + "Id: " + Color.getRESET() + taskId + "\n"
                    + Color.getBLUE() + "Title: " + Color.getRESET() + title + "\n"
                    + Color.getBLUE() + "Description: " + Color.getRESET() + description + "\n"
                    + Color.getBLUE() + "Status: " + Color.getRESET() + status + "\n"
                    + Color.getBLUE() + "Created: " + Color.getRESET() + createdAt + "\n"
                    + Color.getBLUE() + "Updated: " + Color.getRESET() + createdAt;
        }

        return Color.getBLUE() + "Id: " + Color.getRESET() + taskId + "\n"
                + Color.getBLUE() + "Title: " + Color.getRESET() + title + "\n"
                + Color.getBLUE() + "Description: " + Color.getRESET() + description + "\n"
                + Color.getBLUE() + "Status: " + Color.getRESET() + status + "\n"
                + Color.getBLUE() + "Created: " + Color.getRESET() + createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(taskId, task.taskId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(taskId);
    }
}
