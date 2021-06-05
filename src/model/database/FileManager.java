package model.database;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static model.database.Database.USER_BASE_FILENAME;
import static model.database.Database.users;

public class FileManager {

    public static void readUserBase() throws FileNotFoundException {
        ArrayList<User> usersData = new ArrayList<>();
        File file = new File(USER_BASE_FILENAME);
        Scanner scanner = new Scanner(file);
        if(file.canRead())
            while (scanner.hasNext())
                usersData.add(new User(scanner.next(), scanner.next()));
        users = usersData;
    }
    protected static void writeUserBase() throws IOException {
        File file = new File(Database.USER_BASE_FILENAME);
        FileWriter fileWriter = new FileWriter(file);
        if(file.canWrite())
            for (User user : users)
                fileWriter.write(user.getUserName()+ " "+ user.getPassWord()+"\n");
        fileWriter.close();
    }
}