package main.java.utils;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
    private BufferedReader reader;
    private String lastLine;

    /**
     * Constructs a new Reader object that reads from the file at the given path. If an
     * IOException occurs, it will be caught and printed to the console using the
     * printError method. If an error occurs, the program will abort.
     *
     * @param path the path of the file to read from
     * @throws IllegalArgumentException if the path is null or empty
     */
    public Reader(String path) {
        try {
            reader = new BufferedReader(new FileReader(path));
        } catch (IOException e) {
            printError(e);
        }
    }

    /**
     * Reads a line from the file using the reader and returns it as a string. If an
     * IOException occurs, it will be caught and printed to the console using the
     * printError method. If an error occurs, the program will abort.
     *
     * @return the next line of the file as a string, or null if the end of the file
     *         has been reached.
     */
    public String readLine() {
        try {
            lastLine = reader.readLine();
        } catch (IOException e) {
            printError(e);
        }
        return lastLine;
    }

    /**
     * Closes the underlying BufferedReader. Any IOException encountered while
     * closing the BufferedReader is caught and handled by printing an error
     * message to the console.
     */
    public void close() {
        try {
            reader.close();
        } catch (IOException e) {
            printError(e);
        }
    }

    /**
     * Prints an error message to the console and terminates the program.
     *
     * @param e the IOException that occurred.
     */
    private void printError(Exception e) {
        System.out.println("Error reading the file:");
        e.printStackTrace();
        System.out.println("Aborting...");
        System.exit(1);
    }
}
