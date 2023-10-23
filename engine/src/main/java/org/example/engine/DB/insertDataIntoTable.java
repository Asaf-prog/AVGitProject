package org.example.engine.DB;

import java.sql.*;

public class insertDataIntoTable {
    static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String URL = "jdbc:mysql://localhost/mygit";
    static final String USER = "root";
    static final String PASSWORD = "962233";

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {
            // Load the JDBC driver and establish the connection
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            // SQL statement for inserting data
            String sql = "INSERT INTO SH1 (Date, SH1, updateDate) VALUES (?, ?, ?)";

            // Create a PreparedStatement object
            preparedStatement = conn.prepareStatement(sql);

            // Set the values for the parameters
            preparedStatement.setInt(1, 20231004); // Replace with the actual Date (assuming it's an int)
            preparedStatement.setInt(2, 123);      // Replace with the actual SH1 value (assuming it's an int)
            preparedStatement.setInt(3, 20231004); // Replace with the actual updateDate value (assuming it's an int)

            // Execute the PreparedStatement
            preparedStatement.executeUpdate();

            System.out.println("Data inserted successfully.");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.err.println("Error: Unable to insert data into the table.");
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
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
    }
}
