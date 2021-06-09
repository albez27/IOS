package com.company;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.Calendar;
import java.util.Scanner;

public class MiniMaks extends User implements RuleCheck{
    private String Institutes;
    private boolean isUserExist;
    public String getInstitutes() {
        return Institutes;
    }

    public void setInstitutes(String institutes) {
        Institutes = institutes;
    }

    @Override
    public void ruleCheck() throws SQLException {
        isUserExist = false;
        try (PreparedStatement ps = connection.prepareStatement("select 1 from users where Login = ?")) {
            ps.setString(1, login);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    isUserExist = true;
                }
            }
        }
        if (isUserExist) {
            System.out.println("Пользователь существует!");

        }
    }
    public void connection() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Calendar calendar = Calendar.getInstance();
        java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        }

        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
    }
    public void registrationUser() throws IOException, SQLException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        connection();
        for (; ; ) {
            System.out.println("Хотите ли вы добавить пользователя?\n 1 - Да, 2 - Нет");
            Scanner sc = new Scanner(System.in);
            int key = sc.nextInt();
            if (key == 2) break;
            else {
                System.out.print("Введите логин: ");
                login = reader.readLine();
                ruleCheck();
                if(isUserExist == true) break;
                System.out.print("Введите Емаил: ");
                Email = reader.readLine();
                System.out.print("Введите ФИО: ");
                FIO = reader.readLine();
                System.out.print("Введите пароль: ");
                password = reader.readLine();

                System.out.println("Выберите роль регистрируемого");
                System.out.println("1 - Студент, 2 - Преподаватель, 3 - Мини-Администратор, 0 - Выход");
                int inputKey = sc.nextInt();
                switch (inputKey) {
                    case 1: // Студент
                    {
                        try (Connection conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD)) {
                            System.out.print("Введите название группы: ");
                            Student student = new Student();
                            student.setGroup(reader.readLine());
                            System.out.print("Введите номер курса(1-4): ");
                            student.setCourse(sc.nextInt());
                            if(student.getCourse() < 1 || student.getCourse() > 4)
                            {
                                System.out.println("Ошибка ввода курса, число должно быть в диапазоне [1;4]");
                                break;
                            }
                            String sql = "INSERT INTO users (Login,Email,Password,FIO,GroupDes,Course,idRoles) Values ( ?, ?, ?, ?, ?, ?,?)";
                            PreparedStatement stmt = conn.prepareStatement(sql);
                            stmt.setString(1, login);
                            stmt.setString(2, Email);
                            stmt.setString(3, password);
                            stmt.setString(4, FIO);
                            stmt.setString(5, student.getGroup());
                            stmt.setInt(6,student.getCourse());
                            stmt.setInt(7, 1);
                            stmt.executeUpdate();
                            System.out.println("Пользователь " + login + " добавлен!");
                        }
                        break;
                    }
                    case 2: // Предователь
                    {
                        try (Connection conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD)) {
                            System.out.println("Введите название кафедры: ");
                            Teacher teacher = new Teacher();
                            teacher.setDepartment(reader.readLine());
                            String sql = "INSERT INTO users (Login,Email,Password,FIO,Department,idRoles) Values ( ?, ?, ?, ?, ?, ?)";
                            PreparedStatement stmt = conn.prepareStatement(sql);
                            stmt.setString(1, login);
                            stmt.setString(2, Email);
                            stmt.setString(3, password);
                            stmt.setString(4, FIO);
                            stmt.setString(5, teacher.getDepartment());
                            stmt.setInt(6, 2);
                            stmt.executeUpdate();
                            System.out.println("Пользователь " + login + " добавлен!");
                        }
                        break;
                    }
                    case 3: //мини макс
                    {
                        try (Connection conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD)) {
                            System.out.println("Введите название кафедры: ");
                            MiniMaks minimaks = new MiniMaks();
                            minimaks.setInstitutes(reader.readLine());
                            String sql = "INSERT INTO users (Login,Email,Password,FIO,Institutes,idRoles) Values ( ?, ?, ?, ?, ?, ?)";
                            PreparedStatement stmt = conn.prepareStatement(sql);
                            stmt.setString(1, login);
                            stmt.setString(2, Email);
                            stmt.setString(3, password);
                            stmt.setString(4, FIO);
                            stmt.setString(5, minimaks.getInstitutes());
                            stmt.setInt(6, 3);
                            stmt.executeUpdate();
                            System.out.println("Пользователь " + login + " добавлен!");
                        }
                        break;
                    }

                }
            }
        }
    }
    public void deleteUser() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, IOException, SQLException {
        connection();
        for (; ; ){
        System.out.println("Хотите ли удалить пользователя?\n 1 - Да, 2 - Нет");
        Scanner sc = new Scanner(System.in);
        int key = sc.nextInt();
        if (key == 2) break;
        else
        {
            System.out.print("Введите логин удаляемого пользователя: ");
            login = reader.readLine();
            ruleCheck();
            if(isUserExist == true)
            {
                try (Connection conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD)) {
                    String sql = "DELETE FROM users WHERE Login =?";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1,login);
                    stmt.executeUpdate();
                    System.out.println("Пользователь " + login + " удален!");
                }
                break;
            }
            else System.out.println("Пользователя с таким логином нет в системе");
        }
    }
    }
    public void createNod()
    {

    }
    public void updateCourse()
    {

    }
    public void cleanForum()
    {

    }
    public void managementCurriculum()
    {

    }
}
