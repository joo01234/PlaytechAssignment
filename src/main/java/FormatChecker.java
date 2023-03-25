package main.java;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatChecker {
    public static boolean isCorrectLineFormat(String line) {
        String[] lineArr = line.split(",");
        if (lineArr.length != 6) {
            return false;
        }

        return isCorrectActionFormat(lineArr[3]);
    }

    private static boolean isCorrectActionFormat(String elem) {
        Pattern pattern = Pattern.compile("^[PD] [A-Za-z]+$");
        Matcher matcher = pattern.matcher(elem);
        return matcher.matches();
    }

    public static boolean isCorrectCardFormat(String elem) {
        Pattern pattern = Pattern.compile("^(?:(?:[2-9JQKAjqka]|10)[HDSChdsc]|\\?)$");
        Matcher matcher = pattern.matcher(elem);
        return matcher.matches();
    }
}
