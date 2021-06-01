package view.menu;

import controller.LoginController;
import controller.SignupController;

public class StartMenu extends Menu{
    @Override
    public Menu run() {
        Menu menu = this;
        switch (getCommand("(start menu)LOGIN/SIGNUP: ")) {
            case "LOGIN" -> menu = new LoginMenu(new LoginController());
            case "SIGNUP" -> menu = new SignupMenu(new SignupController());
            case "EXIT" -> menu = null;
            default -> System.out.println("Invalid command");
        }
        return menu;
    }
}
