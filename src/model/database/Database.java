package model.database;

import controller.mission.Mission;
import java.io.IOException;
import java.util.ArrayList;

public class Database {
    final static String USER_BASE_FILENAME = "user base.txt";
    final static String MISSIONS_FILENAME = "missions.txt";
    protected static ArrayList<User> users;
    protected static ArrayList<Mission> missions;

    private Database() { }

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
            if (user.getUsername().equals(userName.toLowerCase()))
                return user;
        return null;
    }

    public static ArrayList<Mission> getMissions() { return missions; }


}
