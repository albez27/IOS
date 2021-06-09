package com.company;

import java.util.Scanner;

public class Institutes {
        private String instituteName; //Название института
        private String instituteCount; //Кол-во институтов
        public static void ListInstitutes()
        {
            Scanner input = new Scanner(System.in);
            do {
                System.out.println("\n\n          Список инситутов");
                System.out.println("--------------------------------------");
                System.out.println("1 - ИнПит");
                System.out.println("2 - ИММ");
                System.out.println("3 - ФТИ");
                System.out.println("4 - ИнЭтс");
                System.out.println("5 - Урбас");
                System.out.print("\nВыберите пункт: ");
                getInput(input.next());
            }
            while(true);
        }
        private static void getInput(String input) {
            switch(Integer.parseInt(input)) {
                case 1: {

                    break;
                }

                default:
                    System.out.print("Ошибка при вводе");
                    break;
            }
        }

        public String getInstituteName() {
            return instituteName;
        }

        public void setInstituteName(String instituteName) {
            this.instituteName = instituteName;
        }

        public String getInstituteCount() {
            return instituteCount;
        }

        public void setInstituteCount(String instituteCount) {
            this.instituteCount = instituteCount;
        }
    }


