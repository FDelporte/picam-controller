package be.webtechie.picamcontroller.exec;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class Executor {
    private static final Logger LOGGER = LogManager.getLogger(Executor.class.getName());

    public static void getCommandOutput(String command) {
        try {
            // Split the command into parts (use the shell if necessary)
            String[] commandParts = {"/bin/sh", "-c", command};

            // Use ProcessBuilder to run the command
            ProcessBuilder processBuilder = new ProcessBuilder(commandParts);

            // Redirect output or error streams to avoid blocking
            processBuilder.redirectOutput(ProcessBuilder.Redirect.DISCARD);
            processBuilder.redirectError(ProcessBuilder.Redirect.DISCARD);

            // Start the process
            processBuilder.start();

            // No waitFor(), the process runs independently
            System.out.println("Command started: " + command);


        } catch (IOException ex) {
            LOGGER.error("Could not execute '{}': {}", command, ex.getMessage());
        }
    }
}
