package com.quizapp.quizapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSingleton {


    private static final String DB_NAME="quizapp";
    private static final String URL="jdbc:mysql://127.0.0.1/"+DB_NAME;
    private static final String USERNAME="root";
    private static final String PASSWORD="";


    public static Connection getConnection(){
        Connection connection = null;
        try{
            connection=DriverManager.getConnection(URL,USERNAME,PASSWORD);
            System.out.println("Database Connection successful");
        }catch (SQLException sq){
            System.out.println(sq.getMessage());
        }


        return connection;
    }
}

