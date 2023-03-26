package main.java.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {
    private BufferedWriter writer;

    /**
     * Constructs a new Writer object to write to a file specified by the given path.
     * If the file does not exist, a new file is created. If an IOException occurs during
     * construction, the method will print an error message.
     *
     * @param path the path of the file to write to
     */
    public Writer(String path) {
        try {
            writer = new BufferedWriter(new FileWriter(path));
        } catch (IOException e) {
            printError(e);
        }
    }

    /**
     * Writes a line to the file using the writer. If an IOException occurs,
     * it will be caught and printed to the console using the printError method.
     */
    public void writeLine(String line) {
        try {
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            printError(e);
        }
    }

    /**
     * Closes the underlying BufferedWriter. Any IOException encountered while
     * closing the BufferedWriter is caught and handled by printing an error
     * message to the console.
     */
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            printError(e);
        }
    }

    /**
     * Prints an error message to the console.
     *
     * @param e the IOException that occurred.
     */
    private void printError(IOException e) {
        System.out.println("Error writing to file:");
        e.printStackTrace();
    }
}
