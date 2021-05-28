package view.menu;

import model.play.database.User;

public class MainMenu extends Menu{
    User user;

    public MainMenu(User user) {
        this.user = user;
    }

    @Override
    public Menu run() {
        return null;
    }
}
