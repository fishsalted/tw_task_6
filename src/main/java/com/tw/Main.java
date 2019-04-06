package com.tw;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReportCard report_card = new ReportCard();
        String menu_string = "1. 添加学生\n2. 生成成绩单\n3. 退出请输入你的选择（1～3）：";

        while(true) {
            System.out.print(menu_string);
            switch (Integer.parseInt(scanner.nextLine())) {
                case 1:
                    Student student = new Student();
                    System.out.print("请输入学生信息（格式：姓名, 学号, 学科: 成绩, ...），按回车提交：");
                    while(!student.getInputFromScanner(scanner)) {
                        System.out.print("请按正确的格式输入（格式：姓名, 学号, 学科: 成绩, ...）：");
                    }
                    report_card.registerStudent(student);
                    System.out.print("学生" + student.getName() +"的成绩被添加\n");
                    break;
                case 2:
                    System.out.print("请输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：");
                    while(!report_card.getOutputArgumentsFromScanner(scanner)) {
                        System.out.print("请按正确的格式输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：");
                    }
                    break;
                case 3:
                    return;
            }
        }

    }

}
