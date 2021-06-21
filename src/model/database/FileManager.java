package model.database;

import controller.mission.Mission;

import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import static model.database.Database.*;

public class FileManager {

    public static void readUserBase() throws FileNotFoundException {
        ArrayList<User> usersData = new ArrayList<>();
        File file = new File(USER_BASE_FILENAME);
        Scanner scanner = new Scanner(file);
        if (file.canRead())
            while (scanner.hasNext())
                usersData.add(new User(scanner.next(), scanner.next()));
        users = usersData;
    }

    protected static void writeUserBase() throws IOException {
        File file = new File(Database.USER_BASE_FILENAME);
        FileWriter fileWriter = new FileWriter(file);
        if (file.canWrite())
            for (User user : users)
                fileWriter.write(user.getUsername() + " " + user.getPassword() + "\n");
        fileWriter.close();
    }

    public static void readMissions() throws FileNotFoundException {
        ArrayList<Mission> missionsData = new ArrayList<>();
        File file = new File(Database.MISSIONS_FILENAME);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            Mission mission = new Mission();
            try {
                while (true) {
                    String first = scanner.next();
                    if (first.equals("ice"))
                        first += " " + scanner.next();
                    String second = scanner.next();
                    mission.set(first, Integer.parseInt(second));
                }
            } catch (Exception ignored) {
            }
            missionsData.add(mission);
        }
        missions = missionsData;
    }

    public static void readUserData(User user) throws FileNotFoundException {
        File file = new File("users\\" + user.getUsername() + ".txt");
        Scanner scanner = new Scanner(file);
        user.setCurrentMission(scanner.nextInt());
        int[] rewardsData = new int[Mission.numOfMissions];
        for (int i = 0; i < rewardsData.length; i++) {
            rewardsData[i] = scanner.nextInt();
            if (!scanner.hasNext())
                break;
        }
        user.setRewards(rewardsData);
    }
    public static void writeUserData(User user) throws IOException {
        File file = new File("users\\" + user.getUsername() + ".txt");
        FileWriter fileWriter = new FileWriter(file);
        StringBuilder sb = new StringBuilder();
        sb.append(user.getCurrentMission());
        for (int i = 0; i < Mission.numOfMissions; i++)
            sb.append(" ").append(user.getRewards()[i]);
        fileWriter.write(sb.toString());
        fileWriter.flush();
        fileWriter.close();
    }

    public static void log(String message) {
        File file = new File("logger.txt");
        try {
            LocalTime time = LocalTime.now();

            FileWriter fileWriter = new FileWriter(file);
            fileWriter.append(time.toString()+" "+message);
        } catch (IOException e) { e.printStackTrace(); }
    }
    public static void emptyLog(){
        File file = new File("logger.txt");
        file.delete();
    }
}
