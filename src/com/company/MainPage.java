package com.company;
import java.util.Scanner;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class MainPage {
    public static void displayMenu() {
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("\n\n          Добро пожаловать в ИОС");
            System.out.println("--------------------------------------");
            System.out.println("1 - Список инситутов");
            System.out.print("\nВыберите пункт: ");
            getInput(input.next());
        }
        while(true);
    }
    private static void getInput(String input) {
        switch(Integer.parseInt(input)) {
            case 1: {
                Institutes.ListInstitutes();
                break;
            }

            default:
                System.out.print("Ошибка при вводе");
                break;
        }
    }

}
