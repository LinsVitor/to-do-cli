package com.todocli.repository;

import com.todocli.db.ConnectionFactory;
import com.todocli.exception.DbException;
import com.todocli.model.Status;
import com.todocli.model.Task;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SQLiteRepository implements TaskRepository{

    @Override
    public int insert(Task task) {
        String insert = """
                    INSERT INTO tasks (title, description, createdAt, updatedAt, status)
                    VALUES(?, ?, ? ,? , ?)
                    """;
        try(Connection conn = ConnectionFactory.createConnection()) {
            PreparedStatement pst = conn.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, task.getTitle());
            pst.setString(2, task.getDescription());
            pst.setString(3, String.valueOf(task.getCreatedAt()));
            if (task.getUpdatedAt() != null) {
                pst.setString(4, String.valueOf(task.getUpdatedAt()));
            }
            else {
                pst.setNull(4, Types.VARCHAR);
            }
            pst.setString(5, String.valueOf(task.getStatus()));
            pst.executeUpdate();
            return pst.getGeneratedKeys().getInt(1);
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        if (existsId(id)) {
            String delete = """
                    DELETE FROM tasks
                    WHERE taskId = ?
                    """;
            try(Connection conn = ConnectionFactory.createConnection()) {
                PreparedStatement pst = conn.prepareStatement(delete);
                pst.setInt(1, id);
                pst.execute();
            }
            catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    @Override
    public void deleteAll() {
        String deleteAll = """
                    DELETE FROM tasks
                    """;
        try(Connection conn = ConnectionFactory.createConnection()) {
            Statement st = conn.createStatement();
            st.execute(deleteAll);
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void update(int id, Task task) {
        if (existsId(id)) {
            String update = """
                    UPDATE tasks
                    SET title = ?,
                        description = ?,
                        status = ?,
                        updatedAt = ?
                    WHERE taskId = ?
                    """;
            try(Connection conn = ConnectionFactory.createConnection()) {
                PreparedStatement pst = conn.prepareStatement(update);
                pst.setString(1, task.getTitle());
                pst.setString(2, task.getDescription());
                pst.setString(3, String.valueOf(task.getStatus()));
                if (task.getUpdatedAt() != null) {
                    pst.setString(4, String.valueOf(task.getUpdatedAt()));
                }
                else {
                    pst.setNull(4, Types.VARCHAR);
                }
                pst.setInt(5, id);
                pst.execute();
            }
            catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    @Override
    public Task findById(int id) {
       if (existsId(id)) {
           String findById = """
                    SELECT *
                    FROM tasks
                    WHERE taskId = ?;
                    """;
           try(Connection conn = ConnectionFactory.createConnection()) {
               PreparedStatement pst = conn.prepareStatement(findById);
               pst.setInt(1, id);
               ResultSet rs = pst.executeQuery();
               Task task = new Task();
               if (rs.next()) {
                   task = mapResultSetToTask(rs);
               }
               return task;
           }
           catch (SQLException e) {
               throw new DbException(e.getMessage());
           }
       }
       return null;
    }

    @Override
    public List<Task> searchByTitle(String title) {
        String searchByTitle = """
                    SELECT *
                    FROM tasks
                    WHERE title LIKE ?
                    """;
        try(Connection conn = ConnectionFactory.createConnection()) {
            PreparedStatement pst = conn.prepareStatement(searchByTitle);
            pst.setString(1, "%" + title + "%");
            ResultSet rs = pst.executeQuery();
            List<Task> tasks = new ArrayList<>();
            while (rs.next()) {
                Task task = mapResultSetToTask(rs);
                tasks.add(task);
            }
            return tasks;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<Task> findAll() {
        String findAll = """
                    SELECT *
                    FROM tasks
                    """;
        try(Connection conn = ConnectionFactory.createConnection()) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(findAll);
            List<Task> tasks = new ArrayList<>();
            while (rs.next()) {
                Task task = mapResultSetToTask(rs);
                tasks.add(task);
            }
            return tasks;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<Task> findByStatus(Status status) {
        String findByStatus = """
                    SELECT *
                    FROM tasks
                    WHERE status = ?
                    """;
        try(Connection conn = ConnectionFactory.createConnection()) {
            PreparedStatement pst = conn.prepareStatement(findByStatus);
            pst.setString(1, String.valueOf(status));
            ResultSet rs = pst.executeQuery();
            List<Task> tasks = new ArrayList<>();
            while (rs.next()) {
                Task task = mapResultSetToTask(rs);
                tasks.add(task);
            }
            return tasks;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    private boolean existsId(int id) {
        String existsId = """
                    SELECT EXISTS(
                        SELECT 1
                        FROM tasks
                        WHERE taskId = ?
                    );
                    """;
        try(Connection conn = ConnectionFactory.createConnection()) {
            PreparedStatement pst = conn.prepareStatement(existsId);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            int result = rs.getInt(1);
            return result > 0;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    private Task mapResultSetToTask(ResultSet rs) throws SQLException {
        Task task = new Task();
        task.setTaskId(rs.getInt("taskId"));
        task.setTitle(rs.getString("title"));
        task.setDescription(rs.getString("description"));
        task.setCreatedAt(LocalDateTime.parse(rs.getString("createdAt")));
        if (rs.getString("updatedAt") != null) {
            task.setUpdatedAt(LocalDateTime.parse(rs.getString("updatedAt")));
        }
        task.setStatus(Status.fromString(rs.getString("status")));
        return task;
    }
}
