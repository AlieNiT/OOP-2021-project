package controller;

import model.database.Database;
import model.database.User;

public class LoginController {

    public User getUser(String userName){
        return Database.getUser(userName);
    }

    public String checkPassUserFormat(String userData,String type) {
        //TODO
        if (!userData.equals(userData.replaceAll("( )+", "")))
            throw new RuntimeException("Wrong format of "+type);
        return userData;
    }
}
