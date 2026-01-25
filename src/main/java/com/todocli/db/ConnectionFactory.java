package com.todocli.db;

import java.io.File;
import java.sql.*;

public class ConnectionFactory {

    private static final File dataDir = new File("data");
    private static String URL = "jdbc:sqlite:data" + File.separator + "tasks.db";

    public static Connection CreateConnection() {
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }
        Statement st = null;
        try {
            Connection conn = DriverManager.getConnection(URL);
            String createDb = """
                            CREATE TABLE IF NOT EXISTS tasks (
                            taskId INTEGER PRIMARY KEY,
                            title TEXT NOT NULL,
                            description TEXT NOT NULL,
                            createdAt TEXT,
                            updatedAt TEXT,
                            status TEXT
                            )
                            """;
            st = conn.createStatement();
            st.execute(createDb);
            return conn;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            close(st);
        }
    }

    public static <T extends AutoCloseable> void close(T t) {
        if (t != null) {
            try {
                t.close();
            }
            catch (Exception e) {
                throw new DbException(e.getMessage());
            }
        }
    }
}
