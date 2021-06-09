package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class MainAdmin extends User{
    public boolean ruleCheck(String login) throws SQLException {
        ResultSet rs = statement.executeQuery("SELECT Login FROM users");

        while (rs.next()) {
            if (login.contains(rs.getString(1)))
                return false;

        }
        return true;
    }
    public void RegistrationAdmin() throws IOException, SQLException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {
        //  Calendar calendar = Calendar.getInstance();
        //java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        }

        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        for (; ; ) {
            System.out.println("Хотите ли вы добавить пользователя?\n 1 - Да, 2 - Нет");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int key = Integer.parseInt(reader.readLine());
            if (key == 2) break;
            else {
                System.out.print("Введите логин: ");
                if (ruleCheck(login = reader.readLine()) == false) break;
                System.out.print("Введите email: ");
                Email = reader.readLine();
                System.out.print("Введите ФИО: ");
                FIO = reader.readLine();
                System.out.print("Введите пароль: ");
                password = reader.readLine();
                System.out.println("Выберите роль регистрируемого");
                System.out.println("1 - Мини-Администратор, 2 - Администратор, 0 - Выход");
                int inputKey = Integer.parseInt(reader.readLine());

                switch (inputKey) {
                    case 1: // Мини макс
                    {
                        try (Connection conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD)) {
                            System.out.println("Введите институт: ");
                            User.showInstitutes();
                            int inst = Integer.parseInt(reader.readLine());
                            String sql = "INSERT INTO ios.users (Login,Email,Password,FIO,idRoles,idInstitute) Values ( ?, ?, ?, ?, ?, ?)";
                            PreparedStatement stmt = conn.prepareStatement(sql);
                            stmt.setString(1, login);
                            stmt.setString(2, Email);
                            stmt.setString(3, password);
                            stmt.setString(4, FIO);
                            stmt.setInt(5, 3);              // !!! Указать правильную роль мини максу
                            stmt.setInt(6, inst);               // !!! idRoles и idInst поменять местами. У
                            stmt.executeUpdate();
                            System.out.println("Пользователь " + login + " добавлен!");
                        }
                        break;
                    }
                    case 2: // Макси макс
                    {
                        try (Connection conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD)) {
                            String sql = "INSERT INTO ios.users (Login,Email,Password,FIO,idRoles,idInstitute) Values ( ?, ?, ?, ?, ?, ?)";
                            PreparedStatement stmt = conn.prepareStatement(sql);
                            stmt.setString(1, login);
                            stmt.setString(2, Email);
                            stmt.setString(3, password);
                            stmt.setString(4, FIO);
                            stmt.setInt(5, 4);
                            stmt.setInt(6, 0);
                            stmt.executeUpdate();
                            System.out.println("Пользователь " + login + " добавлен!");
                        }
                        break;
                    }
                }
            }
        }
    }
    public void DeleteAdmin() throws SQLException, IOException {
        System.out.print("Введите логин удаляемого пользователя: ");
        login = reader.readLine();
        ResultSet rs = statement.executeQuery("SELECT idUser,Login,idRoles FROM ios.users");

        while (rs.next()) {
            if (rs.getString(2).contains(login) && (rs.getInt(3) == 3 || rs.getInt(3) == 4)) { // !!! 5 и 6 это роли мини и макси макса
                String sql = "DELETE FROM users WHERE Login =?";
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1, login);
                stmt.executeUpdate();
                System.out.println("Пользователь " + login + " удален!");
            }
        }
        rs.close();
    }
}
