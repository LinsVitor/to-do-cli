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
            case TODO -> Color.getYELLOW();
            case IN_PROGRESS -> Color.getBLUE();
            case DONE -> Color.getGREEN();
        };
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String formattedCreatedAt = createdAt.format(formatter);
        String formattedUpdatedAt = updatedAt != null ? updatedAt.format(formatter) : "N/A";

        return " " + Color.getBrightBlack() + "----------------------------------------\n" + Color.getRESET() +
                Color.getCYAN() + " ID: " + Color.getRESET() + taskId + "\n" +
                Color.getCYAN() + " Title: " + Color.getRESET() + title + "\n" +
                Color.getCYAN() + " Description: " + Color.getRESET() + description + "\n" +
                Color.getCYAN() + " Status: " + getStatusColor() + status + Color.getRESET() + "\n" +
                Color.getCYAN() + " Created at: " + Color.getRESET() + formattedCreatedAt + "\n" +
                Color.getCYAN() + " Updated at: " + Color.getRESET() + formattedUpdatedAt + "\n" +
                " " + Color.getBrightBlack() + "----------------------------------------" + Color.getRESET();
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
