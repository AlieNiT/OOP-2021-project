package model.play.database;

public class User {
    String userName;
    String passWord;
    public User(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }
    public String getUserName() {
        return userName;
    }
    protected void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassWord() {
        return passWord;
    }
    protected void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
