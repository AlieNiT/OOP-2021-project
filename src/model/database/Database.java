package model.database;

import java.util.ArrayList;

public class Database {
    private static Database database;
    ArrayList<User> users;
    {
        users = new ArrayList<>();
    }
    public static Database getInstance() {
        if (database == null)
            database = new Database();
        return database;
    }
    private Database() {
    }
    public static User makeUser(String userName,String passWord){
        User user = new User(userName,passWord);
        database.users.add(user);
        return user;
    }
    public static boolean userExists(String userName){
        return true;
    }
    public static User getUser(String userName) {
        if (userExists(userName))
            for (User user : database.users) {
                if (user.getUserName().equals(userName))
                    return user;
            }
        return null;
    }
}
