package main.java.utils;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
    private BufferedReader reader;
    private String lastLine;

    public void readFile(String path) {
        try {
            reader = new BufferedReader(new FileReader(path));
        } catch (IOException e) {
            printError(e);
        }
    }

    public String readLine() {
        try {
            lastLine = reader.readLine();
        } catch (IOException e) {
            printError(e);
        }
        return lastLine;
    }

    private void printError(IOException e) {
        System.out.println("Error reading the file:");
        e.printStackTrace();
        System.out.println("Aborting...");
        System.exit(1);
    }
}
