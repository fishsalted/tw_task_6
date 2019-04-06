package com.tw;

import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Student {

    private int id_;
    private String name_;

    private double math_score_;
    private double coding_score_;
    private double chinese_score_;
    private double english_score_;

    private double avg_score_;
    private double total_score_;

    public Student() { }

    public boolean getInputFromScanner(Scanner scanner) {
        return parseFromString(scanner.nextLine());
    }

    public boolean getInputFromString(String string) {
        return parseFromString(string);
    }

    public int getId() {
        return id_;
    }

    public String getName() {
        return name_;
    }

    public double getMathScore() {
        return math_score_;
    }

    public double getCodingScore() {
        return coding_score_;
    }

    public double getChineseScore() {
        return chinese_score_;
    }

    public double getEnglishScore() {
        return english_score_;
    }

    public double getAvgScore() {
        return avg_score_;
    }

    public double getTotalScore() {
        return total_score_;
    }

    private boolean parseFromString(String string) {
        Map<String,Double> score_map = new HashMap<>();
        //模式串
        String regex = "^([\\u4e00-\\u9fa5]+), ([1-9]\\d+), " +
                       "([\\u4e00-\\u9fa5]+): ([1-9]\\d+\\.\\d+|0\\.\\d+|\\d+), " +
                       "([\\u4e00-\\u9fa5]+): ([1-9]\\d+\\.\\d+|0\\.\\d+|\\d+), " +
                       "([\\u4e00-\\u9fa5]+): ([1-9]\\d+\\.\\d+|0\\.\\d+|\\d+), " +
                       "([\\u4e00-\\u9fa5]+): ([1-9]\\d+\\.\\d+|0\\.\\d+|\\d+)$";

        Matcher matcher = Pattern.compile(regex).matcher(string);
        if(!matcher.find()) {
            return false;
        }

        name_ = matcher.group(1);
        id_ = Integer.parseInt(matcher.group(2));

        for(int i = 3; i < 11; i += 2) {
            score_map.put(matcher.group(i), Double.parseDouble(matcher.group(i + 1)));
        }

        math_score_    = score_map.getOrDefault("数学", 0.0);
        coding_score_  = score_map.getOrDefault("编程", 0.0);
        chinese_score_ = score_map.getOrDefault("语文", 0.0);
        english_score_ = score_map.getOrDefault("英语", 0.0);

        total_score_ = math_score_ + coding_score_ + chinese_score_ + english_score_;
        avg_score_ = total_score_ / 4;

        return true;
    }

}
