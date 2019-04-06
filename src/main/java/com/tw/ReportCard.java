package com.tw;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReportCard {

    private List<Student> student_list_;

    public ReportCard() {
        student_list_ = new ArrayList<>();
    }

    public void registerStudent(Student student) {
        for(Student stu : student_list_) {
            if(stu.getId() == student.getId()) {
                return;
            }
        }
        student_list_.add(student);
    }

    public boolean getOutputArgumentsFromScanner(Scanner scanner) {
        return printReportCard(scanner.nextLine());
    }

    public boolean getOutputArgumentsFromString(String string) {
        return printReportCard(string);
    }

    private double getAvgTotalScore() {
        double total_score = 0.0;
        for(Student student : student_list_) {
            total_score += student.getTotalScore();
        }
        if(student_list_.isEmpty()) {
            return 0;
        }
        return total_score / student_list_.size();
    }

    private double getMiddleTotalScore() {
        List<Double> total_score_list = new ArrayList<>();
        int list_number = student_list_.size();
        if(list_number == 0) {
            return 0;
        }
        for(Student student : student_list_) {
            total_score_list.add(student.getTotalScore());
        }
        total_score_list.sort(Comparator.comparingDouble(n -> n));
        if(list_number % 2 == 0) {
            return (total_score_list.get(list_number / 2 - 1) + total_score_list.get(list_number / 2)) / 2;
        }else {
            return total_score_list.get(list_number / 2);
        }
    }

    private boolean printReportCard(String string) {
        List<Student> student_list = new ArrayList<>();
        //模式串
        String regex = "^([1-9]\\d+, )*([1-9]\\d+)$";
        Matcher matcher = Pattern.compile(regex).matcher(string);

        if(!matcher.find()) {
            return false;
        }

        regex = "([1-9]\\d+)";
        matcher = Pattern.compile(regex).matcher(string);

        while(matcher.find()) {
            int stu_id = Integer.parseInt(matcher.group());
            for(Student stu : student_list_) {
                if(stu_id == stu.getId()) {
                    student_list.add(stu);
                    break;
                }
            }
        }

        System.out.print("成绩单\n" +
                         "姓名|数学|语文|英语|编程|平均分|总分\n" +
                         "========================\n");

        for(Student stu : student_list) {
            System.out.println(stu.getName() + "|" +
                               stu.getMathScore() + "|" +
                               stu.getChineseScore() + "|" +
                               stu.getEnglishScore() + "|" +
                               stu.getCodingScore() + "|" +
                               stu.getAvgScore() + "|" +
                               stu.getTotalScore() + "|");
        }

        System.out.print("========================\n" +
                         "全班总分平均数：" + getAvgTotalScore() + "\n" +
                         "全班总分中位数：" + getMiddleTotalScore() + "\n");

        return true;
    }

}
