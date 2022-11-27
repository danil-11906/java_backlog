package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;

public class ConnectionManager {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tsisa2", "postgres", "ilzira1010");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void closeConnection(){
        try {
            if(connection != null){
                connection.close();
                connection = null;
                System.out.println("INFO: Connection CLOSE");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
