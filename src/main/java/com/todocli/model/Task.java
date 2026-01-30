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
        createdAt = LocalDateTime.now();
    }

    public Integer getTaskId() {
        return taskId;
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

    private String getStatusColor() {
        return switch (status) {
            case TODO -> Color.YELLOW;
            case IN_PROGRESS -> Color.BLUE;
            case DONE -> Color.GREEN;
        };
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String formattedCreatedAt = createdAt.format(formatter);
        String formattedUpdatedAt = updatedAt != null ? updatedAt.format(formatter) : "N/A";

        return " " + Color.BRIGHT_BLACK + "----------------------------------------\n" + Color.RESET +
                Color.CYAN + " ID: " + Color.RESET + taskId + "\n" +
                Color.CYAN + " Title: " + Color.RESET + title + "\n" +
                Color.CYAN + " Description: " + Color.RESET + description + "\n" +
                Color.CYAN + " Status: " + getStatusColor() + status + Color.RESET + "\n" +
                Color.CYAN + " Created at: " + Color.RESET + formattedCreatedAt + "\n" +
                Color.CYAN + " Updated at: " + Color.RESET + formattedUpdatedAt + "\n" +
                " " + Color.BRIGHT_BLACK + "----------------------------------------" + Color.RESET;
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
