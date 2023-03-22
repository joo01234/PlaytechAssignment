package main.java;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputChecker {
    public static boolean isCorrectLineFormat(String line) {
        String[] lineArr = line.split(",");
        if (lineArr.length != 6) {
            return false;
        }
        for (int i = 0; i < 6; i++) {
            if (i < 3) {
                if (!isNumber(lineArr[i])) {
                    System.out.println(i);
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isNumber(String elem) {
        Pattern pattern = Pattern.compile("^[0-9]+$");
        Matcher matcher = pattern.matcher(elem);
        if (!matcher.matches()) {
            return false;
        }
        return true;
    }
}
