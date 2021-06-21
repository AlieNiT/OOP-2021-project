package view.menu.exceptions;

import controller.mission.Log;

public class GameErrorException extends RuntimeException {
    String message;
    public GameErrorException(String s) {
        Log.logger.info("GameErrorException with message \"" + s + "\" was thrown.");
        message = s;
    }
    public GameErrorException() {
        Log.logger.info("Invalid command was entered; GameErrorException was thrown.");
        message = "Invalid command";
    }
    @Override
    public String getMessage() {
        return message;
    }
}
