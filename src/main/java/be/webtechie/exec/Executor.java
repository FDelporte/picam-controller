package be.webtechie.exec;

import java.io.*;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Executor {
    private static final Logger LOGGER = LogManager.getLogger(Executor.class.getName());

    public static CommandResult getCommandOutput(String command) {
        boolean finished = false;
        String outputMessage = "";
        String errorMessage = "";

        ProcessBuilder builder = new ProcessBuilder();
        builder.command("sh", "-c", command);

        try {
            Process process = builder.start();

            OutputStream outputStream = process.getOutputStream();
            InputStream inputStream = process.getInputStream();
            InputStream errorStream = process.getErrorStream();

            outputMessage = readStream(inputStream);
            errorMessage = readStream(errorStream);

            finished = process.waitFor(30, TimeUnit.SECONDS);
            outputStream.flush();
            outputStream.close();

            if (!finished) {
                process.destroyForcibly();
            }
        } catch (IOException ex) {
            errorMessage = "IOException: " + ex.getMessage();
        } catch (InterruptedException ex) {
            errorMessage = "InterruptedException: " + ex.getMessage();
        }

        if (!finished || !errorMessage.isEmpty()) {
            LOGGER.error("Could not execute '{}' to detect the board model: {}", command, errorMessage);
            return new CommandResult(false, outputMessage, errorMessage);
        }

        return new CommandResult(true, outputMessage, errorMessage);
    }

    private static String readStream(InputStream inputStream) {
        StringBuilder rt = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                rt.append(line);
            }
        } catch (Exception ex) {
            rt.append("ERROR: ").append(ex.getMessage());
        }
        return rt.toString();
    }
}
