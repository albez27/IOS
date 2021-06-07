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
    private int ID;
    private String login;
    private String Email;
    private String FIO;
    private String password;
    private int key;
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "1234";
    public static final String URL = "jdbc:mysql://localhost:3306/ios";
    public static Statement statement;
    public static Connection connection;
    public void registration() throws IOException, InterruptedException, ClassNotFoundException, SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Calendar calendar = Calendar.getInstance();
        java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

            try{
                connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            } catch (SQLException throwables){
                throwables.printStackTrace();
                throw new RuntimeException();
            }


        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        do {
            ID = count.incrementAndGet();
            System.out.print("Введите логин: ");
            login = reader.readLine();
            System.out.println("Введите Емаил");
            Email = reader.readLine();
            System.out.println("Введите ФИО");
            FIO = reader.readLine();
            System.out.print("Введите пароль: ");
            password = reader.readLine();

            System.out.println("Выберите роль регистрируемого");
            System.out.println("1 - Студент, 2 - Преподаватель, 3 - Мини-Администратор, 0 - Выход");
            Scanner sc = new Scanner(System.in);
            int inputKey  = sc.nextInt();



            switch (inputKey)
            {
                case 1:
                {
                    try (Connection conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD)) {
                        String sql = "INSERT INTO users (idUser,Login,Email,Password,FIO,Roles) Values (?, ?, ?, ?, ?, ?)";
                        PreparedStatement stmt = conn.prepareStatement(sql);
                        stmt.setInt(1,ID);
                        stmt.setString(2, login);
                        stmt.setString(3, Email);
                        stmt.setString(4, FIO);
                        stmt.setString(5, password);
                        stmt.setString(6, "Студент");
                        ID++;
                        stmt.executeUpdate();
                    }
                }
            }
        }
        while(key!=0);
    }

    public void authorization() throws IOException, InterruptedException {
        for (; ; ) {
            System.out.print("Введите логин: ");
            login = reader.readLine();
            System.out.print("Введите пароль: ");
            password = reader.readLine();

            if (login.contains("Безруков_АВ") && password.contains("123")) {
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
            }
        }
    }
    public Forum showDiscipline(Discipline obj){
        return null;
    }
}
