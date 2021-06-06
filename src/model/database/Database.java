package model.database;

import controller.mission.missions.Mission;

import java.io.IOException;
import java.util.ArrayList;

public class Database {
    final static String USER_BASE_FILENAME = "user base.txt";
    final static String MISSIONS_FILENAME = "missions.txt";
    protected static ArrayList<User> users;
    private static ArrayList<Mission> missions = new ArrayList<>();

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
            if (user.getUserName().equals(userName.toLowerCase()))
                return user;
        return null;
    }

    public static void addMission(Mission mission) { missions.add(mission); }
    public static ArrayList<Mission> getMissions() { return missions; }
}
