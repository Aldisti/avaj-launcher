package net.aldisti.avaj;

import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    public static final String LOG_FILE = "simulation.txt";

    private static Logger logger = null;

    private FileWriter writer;

    private Logger() {
        try {
            writer = new FileWriter(LOG_FILE);
        } catch (IOException e) {
            System.out.println("Error: cannot open file: " + LOG_FILE);
            System.exit(10);
        }
    }

    public static Logger getLogger() {
        if (logger == null)
            logger = new Logger();
        return logger;
    }

    public void log(String message) {
        try {
            writer.write(message + "\n");
        } catch (IOException e) {
            System.out.println("Error: cannot write on file " + LOG_FILE);
            System.exit(11);
        }
    }

    public static void close() {
        if (logger == null)
            return;
        try {
            logger.writer.close();
        } catch (IOException ignored) { }
        logger = null;
    }
}
