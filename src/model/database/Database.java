package model.database;

import java.io.IOException;
import java.util.ArrayList;

public class Database {
    final static String USER_BASE_FILENAME = "user base.txt";
    protected static ArrayList<User> users;


    private Database() {
    }

    public static User makeUser(String userName,String passWord){
        User user = new User(userName,passWord);
        Database.users.add(user);
        try {
            FileManager.writeUserBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static User getUser(String userName) {
        for (User user : Database.users)
            if (user.getUserName().equals(userName.toLowerCase()))
                return user;
        return null;
    }
}
