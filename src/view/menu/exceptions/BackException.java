package view.menu.exceptions;

public class BackException extends RuntimeException {
    String message;
    public BackException(String s) {
        message = s;
    }
    public BackException() {
        message = "Invalid command";
    }
    @Override
    public String getMessage() {
        return message;
    }
}

