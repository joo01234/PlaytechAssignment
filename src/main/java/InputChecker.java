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
                    return false;
                }
            }
            else if (i == 3) {
                if (!isCorrectActionFormat(lineArr[i])) {
                    return false;
                }
            }
            else {
                if (!isCorrectHandFormat(lineArr[i])) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isNumber(String elem) {
        Pattern pattern = Pattern.compile("^[0-9]+$");
        Matcher matcher = pattern.matcher(elem);
        return matcher.matches();
    }

    public static boolean isCorrectActionFormat(String elem) {
        Pattern pattern = Pattern.compile("^[PD] [A-Za-z]+$");
        Matcher matcher = pattern.matcher(elem);
        return matcher.matches();
    }

    public static boolean isCorrectHandFormat(String elem) {
        Pattern pattern = Pattern.compile("^-|((?:(?:[2-9JQKAjqka]|10)[HDSChdsc]-)+(?:((?:[2-9JQKAjqka]|10)[HDSChdsc])|\\?))$");
        Matcher matcher = pattern.matcher(elem);
        return matcher.matches();
    }

    public static boolean isCorrectCardFormat(String elem) {
        Pattern pattern = Pattern.compile("^(?:(?:[2-9JQKAjqka]|10)[HDSChdsc]|\\?)$");
        Matcher matcher = pattern.matcher(elem);
        return matcher.matches();
    }
}
