package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException, InterruptedException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
//       MiniMaks m1 = new MiniMaks();
//       m1.registrationUser();
        User u1 = new User();
       u1.showInstitutes();
        MainAdmin ma1 = new MainAdmin();
        ma1.DeleteAdmin();
    }
}
        // ResultSet resultSet = statement.executeQuery("SELECT * FROM Users");
        //while(resultSet.next()){
         //   System.out.println(resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3) + " " + resultSet.getString(4));
       // }


