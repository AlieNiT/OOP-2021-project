package view.menu.exceptions;

public class GameErrorException extends RuntimeException {
    String message;
    public GameErrorException(String s) {
        message = s;
    }
    public GameErrorException() {
        message = "Invalid command";
    }
    @Override
    public String getMessage() {
        return message;
    }
}
