package com.todocli.db;

import com.todocli.exception.DbException;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionFactory {

    private static final File dataDir = new File("data");
    private static String URL = "jdbc:sqlite:data" + File.separator + "tasks.db";

    public static Connection createConnection() {
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }
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
        try {
            Connection conn = DriverManager.getConnection(URL);
            try(Statement st = conn.createStatement()) {
                st.execute(createDb);
            }
            return conn;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }
}
