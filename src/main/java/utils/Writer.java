package main.java.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {
    private BufferedWriter writer;

    public void writeToFile(String path) {
        try {
            writer = new BufferedWriter(new FileWriter(path));
        } catch (IOException e) {
            printError(e);
        }
    }

    public void writeLine(String line) {
        try {
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            printError(e);
        }
    }

    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            printError(e);
        }
    }

    private void printError(IOException e) {
        System.out.println("Error writing to file:");
        e.printStackTrace();
        System.out.println("Aborting...");
        System.exit(1);
    }
}
