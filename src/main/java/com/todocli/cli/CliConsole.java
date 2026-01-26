package com.todocli.cli;

import com.todocli.model.Task;
import com.todocli.util.Color;

import java.util.List;

public class CliConsole {

    public static void println(Object message) {
        IO.println(message);
    }

    public static void printErr(Object message) {
        System.err.println(Color.getRED() + message + Color.getRESET());
    }

    public static void printTasks(List<Task> tasks) {
        if (tasks == null || tasks.isEmpty()) {
            println(Color.getYELLOW() + "No tasks to display." + Color.getRESET());
            return;
        }

        String idHeader = "ID";
        String titleHeader = "Title";
        String statusHeader = "Status";

        String header = String.format("│ %-4s │ %-30s │ %-12s │", idHeader, titleHeader, statusHeader);
        String separator = "├──────┼────────────────────────────────┼──────────────┤";
        String topBorder = "┌──────┬────────────────────────────────┬──────────────┐";
        String bottomBorder = "└──────┴────────────────────────────────┴──────────────┘";

        println(topBorder);
        println(header);
        println(separator);

        for (Task task : tasks) {
            String id = String.format("%-4s", task.getTaskId());
            String title = task.getTitle().length() > 28 ? task.getTitle().substring(0, 28) + ".." : task.getTitle();
            title = String.format("%-30s", title);

            String statusColor = switch (task.getStatus()) {
                case TODO -> Color.getYELLOW();
                case IN_PROGRESS -> Color.getBLUE();
                case DONE -> Color.getGREEN();
            };
            String status = String.format("%-21s", statusColor + task.getStatus() + Color.getRESET());

            println(String.format("│ %s │ %s │ %s │", id, title, status));
        }

        println(bottomBorder);
    }
}
