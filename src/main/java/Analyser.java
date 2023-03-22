package main.java;

import main.java.utils.Reader;

public class Analyser {
    public static void main(String[] args) {
        Reader r = new Reader();
        r.readFile("src/main/resources/game_data.txt");
        System.out.println(r.readLine() + "t");
    }
}