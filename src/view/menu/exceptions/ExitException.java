package view.menu.exceptions;

public class ExitException extends RuntimeException {
    String message;
    public ExitException(String s) {
        message = s;
    }
    public ExitException() {
        message = "Invalid command";
    }
    @Override
    public String getMessage() {
        return message;
    }
}
