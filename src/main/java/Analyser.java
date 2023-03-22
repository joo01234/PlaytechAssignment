package main.java;

import main.java.utils.Reader;

public class Analyser {
    public static void main(String[] args) {
        Reader r = new Reader();
        r.readFile("src/main/resources/game_data.txt");
        String line = r.readLine();
        while (line != null) {
            if (!InputChecker.isCorrectLineFormat(line)) {
                System.out.println("1");
            }
            line = r.readLine();
        }
    }


}