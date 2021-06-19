package model.database;

import controller.mission.Mission;

public class User {
    private String username;
    private String password;
    private int currentMission = 1;
    private int[] rewards = new int[Mission.numOfMissions];

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    protected void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    protected void setPassword(String password) {
        this.password = password;
    }

    public void setCurrentMission(int currentMission) {
        if (Mission.numOfMissions>=currentMission)
            this.currentMission = currentMission;
    }

    public void setRewards(int[] rewards) {
        this.rewards = rewards;
    }

    public void setReward(int index,int reward){
        if (index<Mission.numOfMissions)
            rewards[index] = reward;
    }

    public int[] getRewards() {
        return this.rewards;
    }

    public int getCurrentMission() {
        return currentMission;
    }
}
