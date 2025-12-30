package com.automationexercise.utils;

import java.io.IOException;
import java.sql.*;
import java.sql.DriverManager;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class DBUtils {
    private static final Logger logger = LogManager.getLogger(DBUtils.class);
    private static final String DB_URL;
    private static final String DB_USER ;
    private static final String DB_PASS ;
    private static final String CA_CERT ;

    static {
        try {
            DB_URL = PropertiesLoader.loadProperty("db.url");
            DB_USER = PropertiesLoader.loadProperty("db.username");
            DB_PASS = PropertiesLoader.loadProperty("db.password");
            CA_CERT = PropertiesLoader.loadProperty("db.caCert");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean validateUser(String username, String password) {
        String sql = "SELECT COUNT(*) FROM users WHERE username = ? AND password = ?";
        try {
            Properties props = new Properties();
            props.setProperty("user", DB_USER);
            props.setProperty("password", DB_PASS);
            props.setProperty("sslMode", "VERIFY_CA");   // SSL properties using the same config style
            props.setProperty("sslCa", CA_CERT);

            try (Connection conn = DriverManager.getConnection(DB_URL, props);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, username);
                pstmt.setString(2, password);
                ResultSet rs = pstmt.executeQuery();
                rs.next();
                boolean valid = rs.getInt(1) > 0;
                logger.info("User validation result for {}: {}", username, valid);
                return valid;
            }
        } catch (SQLException e) {
            logger.error("Database validation failed", e);
            throw new RuntimeException("DB validation failed", e);
        }
    }

    // Reusable method to execute SELECT queries.
    // It accepts a SQL string with ? placeholders and a varargs( variable-length arguments.) of parameters.
    // Returns the result as a List of Maps (one map per row, column name -> value).
    public static List<Map<String, Object>> executeSelect(String sql, Object... params) {
        List<Map<String, Object>> rows = new ArrayList<>();
        try {
            Properties props = new Properties();
            props.setProperty("user", DB_USER);
            props.setProperty("password", DB_PASS);
            props.setProperty("sslMode", "REQUIRED");   // enforce SSL
            props.setProperty("sslCa", CA_CERT);

            try (Connection conn = DriverManager.getConnection(DB_URL, props);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                System.out.println(pstmt);

                if (params != null) {
                    for (int i = 0; i < params.length; i++) {
                        pstmt.setObject(i + 1, params[i]);
                    }
                }

                try (ResultSet rs = pstmt.executeQuery()) {
                    ResultSetMetaData meta = rs.getMetaData();
                    int colCount = meta.getColumnCount();
                    while (rs.next()) {
                        Map<String, Object> row = new HashMap<>();
                        for (int c = 1; c <= colCount; c++) {
                            String colName = meta.getColumnLabel(c);
                            Object value = rs.getObject(c);
                            row.put(colName, value);
                        }
                        rows.add(row);
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Error executing select query: {}", sql, e);
            throw new RuntimeException("Error executing select query", e);
        }
        return rows;
    }

    // Reusable method to execute INSERT/UPDATE/DELETE queries.
    // Returns number of affected rows.
    public static int executeUpdate(String sql, Object... params) {
        try {
            Properties props = new Properties();
            props.setProperty("user", DB_USER);
            props.setProperty("password", DB_PASS);
            props.setProperty("sslMode", "REQUIRED");   // enforce SSL
            props.setProperty("sslCa", CA_CERT);

            try (Connection conn = DriverManager.getConnection(DB_URL, props);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                if (params != null) {
                    for (int i = 0; i < params.length; i++) {
                        pstmt.setObject(i + 1, params[i]);
                    }
                }

                int affected = pstmt.executeUpdate();
                logger.info("Executed update: {}, affected rows: {}", sql, affected);
                return affected;
            }
        } catch (SQLException e) {
            logger.error("Error executing update: {}", sql, e);
            throw new RuntimeException("Error executing update", e);
        }
    }

}
