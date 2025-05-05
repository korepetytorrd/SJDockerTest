package org.example.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.h2.tools.Server;

public class DatabaseConnection {
    private static final String JDBC_URL = "jdbc:h2:./test"; //sciezka do pliku
    private static final String USER = "sa"; //domyslny uzytkownik
    private static final String PASSWORD =  ""; // domyslne haslo

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }

    public static void startH2Console() throws SQLException {
        Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").start();
        System.out.println("H2 Console dostępna pod: http://localhost:8082");
    }
    
    public static void main (String[] args){

       try {
        startH2Console();
        try(Connection conn = getConnection()){
            System.out.println("polaczenie dziala");
        }
       } catch (Exception e) {
        e.printStackTrace();
       }
    }
}
