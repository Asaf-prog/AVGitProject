package org.example.engine.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTableInDataBase {
    static final String DRIVER = "com.mysql.cj.jdbc.Driver";//com.mysql.jdbc.Driver.."com.mysql.cj.jdbc.Driver"
    static final String URL = "jdbc:mysql://localhost/mygit";
    static final String USER = "root";
    static final String PASSWORD = "962233";

    public static void main(String[] args) {
        Connection conn = null;
        Statement st = null;

        try {
            // Load the JDBC driver
            Class.forName(DRIVER);

            // Establish the connection
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to the database successfully...");
            System.out.println("Creating table in the selected database...");

            st = conn.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS SH1 (SH1 INT NOT NULL, updateDate INT, Date INT)";
            st.executeUpdate(sql);

            System.out.println("Table created in the given database...");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("Error: MySQL JDBC Driver not found.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error: Unable to connect to the database or create the table.");
        } finally {
            try {
                if (st != null)
                    st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Goodbye");
    }
}
