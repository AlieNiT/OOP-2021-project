package controller.menu;

import model.database.User;
import view.menu.Menu;
import view.menu.exceptions.GameErrorException;

import java.io.FileNotFoundException;

import static model.database.FileManager.readUserData;

public class MainController {
    public User user;

    public MainController(User user) {
        try {
            readUserData(user);
        } catch (FileNotFoundException ignored) { }
        this.user = user;
    }

    public Menu chooseMission(String command) {
        if (Integer.parseInt(command) > user.getCurrentMission())
            throw new GameErrorException("You have not unlocked this level.");
        if (Integer.parseInt(command)<1)
            throw new GameErrorException("Invalid command");
        return null;//TODO
    }
}
