package com.company;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class User {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final AtomicInteger count = new AtomicInteger(1);
    protected String login;
    protected String Email;
    protected String FIO;
    protected String password;
    private int key ;
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "1234";
    public static final String URL = "jdbc:mysql://localhost:3306/ios";
    public static Statement statement;
    public static Connection connection;
    static {
            try {
                connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException();
            }
        }
        static {
            try {
                statement = connection.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException();
            }
        }
    public void authorization() throws IOException, InterruptedException, SQLException { // !!! Убрать цикл

        while (true) {
            ResultSet result = statement.executeQuery("SELECT Login,Password FROM users");
            System.out.print("Введите логин: ");
            login = reader.readLine();
            System.out.print("Введите пароль: ");
            password = reader.readLine();

            while (result.next()) { // три попытки сделать...
                String log = result.getString(1);
                String pass = result.getString(2);
                if (login.contains(log) && password.contains(pass)) {
                    System.out.print("Выполняется");
                    for (int i = 0; i < 3; i++) {
                        Thread.sleep(1000);
                        System.out.print(".");
                    }
                    System.out.println();
                    System.out.println("Вход успешно выполнен");
                    break;
                } else {
                    System.out.println("Неверные данные");
                    break;
                }
            }
            break;
        }
    }
    public static void showInstitutes() throws SQLException {
        int ctr = 1;
        ResultSet result = statement.executeQuery("SELECT InstituteName FROM institute");
        while (result.next()) {
            System.out.println(ctr++ +")"+result.getString(1));
        }
    }
    public Forum showDiscipline(Discipline obj){
        return null;
    }
}
