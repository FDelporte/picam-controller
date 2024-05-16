package be.webtechie.exec;

public class CommandResult {
    private final boolean success;
    private final String outputMessage;
    private final String errorMessage;

    public CommandResult(boolean success, String outputMessage, String errorMessage) {
        this.success = success;
        this.outputMessage = outputMessage;
        this.errorMessage = errorMessage;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getOutputMessage() {
        return outputMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
