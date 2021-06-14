package controller.menu;

import model.database.Database;
import model.database.User;
import view.menu.exceptions.GameErrorException;

public class LoginController {

    public User getUser(String userName){
        return Database.getUser(userName);
    }

    public String checkPassUserFormat(String userData,String type) {
        if (!userData.equals(userData.replaceAll("( )+", "")))
            throw new GameErrorException("Wrong format of "+type);
        return userData;
    }
}
