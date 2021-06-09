package com.company;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Student extends User{
    private String group;
    private int Course;

    public int getCourse() {
        return Course;
    }

    public void setCourse(int course) {
        Course = course;
    }

    public void setGroup(String group) {
        this.group = group;
    }
    public String getGroup() {
        return group;
    }


    public Discipline showDiscipline(){
        return null;
    }
    public Forum showDiscipline(Discipline obj){
        return null;
    }
    public Message sendMessage(Forum obj){
            return null;
    }
}
