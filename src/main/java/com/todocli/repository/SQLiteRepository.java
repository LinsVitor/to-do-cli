package com.todocli.repository;

import com.todocli.db.ConnectionFactory;
import com.todocli.db.DbException;
import com.todocli.model.Status;
import com.todocli.model.Task;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SQLiteRepository implements TaskRepository{

    @Override
    public int insert(Task task) {
        PreparedStatement pst = null;
        try(Connection conn = ConnectionFactory.CreateConnection()) {
            String insert = """
                    INSERT INTO tasks (title, description, createdAt, updatedAt, status)
                    VALUES(?, ?, ? ,? , ?)
                    """;

            pst = conn.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

            pst.setString(1, task.getTitle());
            pst.setString(2, task.getDescription());
            pst.setString(3, String.valueOf(task.getCreatedAt()));
            pst.setString(4, String.valueOf(task.getUpdatedAt()));
            pst.setString(5, String.valueOf(task.getStatus()));

            pst.executeUpdate();
            return pst.getGeneratedKeys().getInt(1);
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            ConnectionFactory.close(pst);
        }
    }

    @Override
    public void delete(int id) {
        if (existsId(id)) {
            PreparedStatement pst = null;
            try(Connection conn = ConnectionFactory.CreateConnection()) {
                String delete = """
                    DELETE FROM tasks
                    WHERE taskId = ?
                    """;

                pst = conn.prepareStatement(delete);
                pst.setInt(1, id);
                pst.execute();
            }
            catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
            finally {
                ConnectionFactory.close(pst);
            }
        }
    }

    @Override
    public void deleteAll() {
        Statement st = null;
        try(Connection conn = ConnectionFactory.CreateConnection()) {
            String deleteAll = """
                    DELETE FROM tasks
                    """;
            st = conn.createStatement();
            st.execute(deleteAll);
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            ConnectionFactory.close(st);
        }
    }

    @Override
    public void update(int id, Task task) {
        if (existsId(id)) {
            PreparedStatement pst = null;
            try(Connection conn = ConnectionFactory.CreateConnection()) {
                String update = """
                    UPDATE tasks
                    SET title = ?,
                        description = ?,
                        status = ?,
                        updatedAt = ?
                    WHERE taskId = ?
                    """;
                pst = conn.prepareStatement(update);
                pst.setString(1, task.getTitle());
                pst.setString(2, task.getDescription());
                pst.setString(3, String.valueOf(task.getStatus()));
                pst.setString(4, String.valueOf(task.getUpdatedAt()));
                pst.setInt(5, id);
                pst.execute();
            }
            catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
            finally {
                ConnectionFactory.close(pst);
            }
        }
    }

    @Override
    public Task findById(int id) {
       if (existsId(id)) {
           PreparedStatement pst = null;
           ResultSet rs = null;
           try(Connection conn = ConnectionFactory.CreateConnection()) {
               String findById = """
                    SELECT *
                    FROM tasks
                    WHERE taskId = ?;
                    """;
               pst = conn.prepareStatement(findById);
               pst.setInt(1, id);
               rs = pst.executeQuery();
               Task task = new Task();
               while (rs.next()) {
                   task.setTaskId(rs.getInt("taskId"));
                   task.setTitle(rs.getString("title"));
                   task.setDescription(rs.getString("description"));
                   task.setCreatedAt(LocalDateTime.parse(rs.getString("createdAt")));
                   if (!rs.getString("updatedAt").equals("null")) {
                       task.setUpdatedAt(LocalDateTime.parse(rs.getString("updatedAt")));
                   }
                   task.setStatus(Status.valueOf(rs.getString("status")));
               }
               return task;
           }
           catch (SQLException e) {
               throw new DbException(e.getMessage());
           }
           finally {
               ConnectionFactory.close(pst);
               ConnectionFactory.close(rs);
           }
       }
       return null;
    }

    @Override
    public List<Task> searchByTitle(String title) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try(Connection conn = ConnectionFactory.CreateConnection()) {
            String searchByTitle = """
                    SELECT *
                    FROM tasks
                    WHERE title LIKE ?
                    """;
            pst = conn.prepareStatement(searchByTitle);
            pst.setString(1, "%" + title + "%");
            rs = pst.executeQuery();
            List<Task> tasks = new ArrayList<>();
            while (rs.next()) {
                Task task = new Task();
                task.setTaskId(rs.getInt("taskId"));
                task.setTitle(rs.getString("title"));
                task.setDescription(rs.getString("description"));
                task.setCreatedAt(LocalDateTime.parse(rs.getString("createdAt")));
                if (!rs.getString("updatedAt").equals("null")) {
                    task.setUpdatedAt(LocalDateTime.parse(rs.getString("updatedAt")));
                }
                task.setStatus(Status.valueOf(rs.getString("status")));
                tasks.add(task);
            }
            return tasks;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            ConnectionFactory.close(pst);
            ConnectionFactory.close(rs);
        }
    }

    @Override
    public List<Task> findAll() {
        Statement st = null;
        ResultSet rs = null;
        try(Connection conn = ConnectionFactory.CreateConnection()) {
            String findAll = """
                    SELECT *
                    FROM tasks
                    """;

            st = conn.createStatement();
            rs = st.executeQuery(findAll);

            List<Task> tasks = new ArrayList<>();
            while (rs.next()) {
                Task task = new Task();
                task.setTaskId(rs.getInt("taskId"));
                task.setTitle(rs.getString("title"));
                task.setDescription(rs.getString("description"));
                task.setCreatedAt(LocalDateTime.parse(rs.getString("createdAt")));
                if (!rs.getString("updatedAt").equals("null")) {
                    task.setUpdatedAt(LocalDateTime.parse(rs.getString("updatedAt")));
                }
                task.setStatus(Status.valueOf(rs.getString("status")));
                tasks.add(task);
            }
            return tasks;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            ConnectionFactory.close(st);
            ConnectionFactory.close(rs);
        }
    }

    @Override
    public List<Task> findByStatus(Status status) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try(Connection conn = ConnectionFactory.CreateConnection()) {
            String findByStatus = """
                    SELECT *
                    FROM tasks
                    WHERE status = ?
                    """;

            pst = conn.prepareStatement(findByStatus);
            pst.setString(1, String.valueOf(status));
            rs = pst.executeQuery();

            List<Task> tasks = new ArrayList<>();
            while (rs.next()) {
                Task task = new Task();
                task.setTaskId(rs.getInt("taskId"));
                task.setTitle(rs.getString("title"));
                task.setDescription(rs.getString("description"));
                task.setCreatedAt(LocalDateTime.parse(rs.getString("createdAt")));
                if (!rs.getString("updatedAt").equals("null")) {
                    task.setUpdatedAt(LocalDateTime.parse(rs.getString("updatedAt")));
                }
                task.setStatus(Status.valueOf(rs.getString("status")));
                tasks.add(task);
            }
            return tasks;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            ConnectionFactory.close(pst);
            ConnectionFactory.close(rs);
        }
    }

    private boolean existsId(int id) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try(Connection conn = ConnectionFactory.CreateConnection()) {
            String existsId = """
                    SELECT EXISTS(
                        SELECT 1
                        FROM tasks
                        WHERE taskId = ?
                    );
                    """;
            pst = conn.prepareStatement(existsId);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            int result = rs.getInt(1);
            return result > 0;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            ConnectionFactory.close(pst);
            ConnectionFactory.close(rs);
        }
    }
}
