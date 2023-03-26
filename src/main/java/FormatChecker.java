package main.java;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatChecker {
    /**
     * Checks if a given line of text follows the correct format for a game log entry.
     *
     * @param line the line of text to check
     * @return true if the line follows the correct format, false otherwise
     */
    public static boolean isCorrectLineFormat(String line) {
        String[] lineArr = line.split(",");
        if (lineArr.length != 6) {
            return false;
        }

        return isCorrectActionFormat(lineArr[3]);
    }

    /**
     * Checks if a given action string has the correct format, which is "P/D Action" where P/D is the player or dealer identifier
     * and Action is a string of alphabetical characters.
     *
     * @param elem the action string to be checked.
     * @return true if the action has the correct format, false otherwise.
     */
    private static boolean isCorrectActionFormat(String elem) {
        Pattern pattern = Pattern.compile("^[PDpd] [A-Za-z]+$");
        Matcher matcher = pattern.matcher(elem);
        return matcher.matches();
    }

    /**
     * Determines if a given string represents a valid playing card.
     * A valid playing card can be either a number (2-10), a face card (J, Q, K, A),
     * followed by one of the four suits (H, D, C, S), or a question mark (?),
     * which represents an unknown card.
     *
     * @param elem the string to check for card format
     * @return true if the string is a valid card format, false otherwise
     */
    public static boolean isCorrectCardFormat(String elem) {
        Pattern pattern = Pattern.compile("^(?:(?:[2-9JQKAjqka]|10)[HDSChdsc]|\\?)$");
        Matcher matcher = pattern.matcher(elem);
        return matcher.matches();
    }
}
