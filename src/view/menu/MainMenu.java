package view.menu;

import controller.menu.MainController;
import model.database.User;

import static view.menu.color.Colors.colorPrintln;

public class MainMenu extends Menu {
    MainController controller;
    boolean shown = false;

    public MainMenu(User user) {
        controller = new MainController(user);
    }

    @Override
    public Menu run() {
        Menu menu = this;
        if (!shown)
            colorPrintln("                >>FARM FRENZY<<\nSTART        SETTINGS        LOGOUT        EXIT");
        shown = false;
        try {
            return switch (getCommand(null)) {
                case "start" -> menu = controller.chooseMission(getCommand("Choose a level (1-" + controller.user.getCurrentMission() + ")"));
                case "settings" -> menu = new SettingsMenu();
                case "logout" -> menu = new StartMenu();
                case "exit" -> menu = null;
                default -> throw new Exception("Invalid command");
            };
        } catch (Exception e) {
            e.printStackTrace();
//            System.out.println(e.getMessage());
//            shown = true;
        }
        return menu;
    }
}
