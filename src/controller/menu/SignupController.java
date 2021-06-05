package controller.menu;

import changes.Colors;
import model.database.Database;
import model.database.User;

public class SignupController {

    public User getUser(String userName){
        return Database.getUser(userName);
    }

    public String checkPassUserFormat(String userData,String type) {
        //TODO
        if (!userData.equals(userData.replaceAll("( )+", "")))
            throw new RuntimeException("Wrong format of "+type);
        return userData;
    }

    public User makeUser(String userName, String passWord) {
        return Database.makeUser(userName,passWord);
    }
}
