package dev.ru.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    public static Connection getConnection(){
        Connection connection = null;
        var dataBase = "students_db";
        var url = "jdbc:mysql://localhost:3306/" + dataBase;
        var user = "root";
        var password = "Master.1";

        //Load MySql driver class in memory
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e){
            System.out.println("An error happened in connection: " + e.getMessage());
        }

        return connection;
    }

    public static void main(String[] args) {
        var connection = ConnectionDB.getConnection();
        if(connection != null){
            System.out.println("OK " + connection);
        } else {
            System.out.println("KO");
        }
    }
}
