package view.menu;

import controller.menu.MainController;
import model.database.User;

public class MainMenu extends Menu{
    MainController controller;
    public MainMenu(User user) {
        controller = new MainController(user);
    }

    @Override
    public Menu run() {
        return null;
    }
}
