package view.menu;

import controller.LoginController;
import controller.SignupController;
import view.menu.exceptions.ExitException;

public class StartMenu extends Menu{
    @Override
    public Menu run() {
        Menu menu = this;
        try {
            switch (getCommand("(start menu)LOGIN/SIGNUP:")) {
                case "LOGIN" -> menu = new LoginMenu(new LoginController());
                case "SIGNUP" -> menu = new SignupMenu(new SignupController());
                default -> System.out.println("Invalid command");
            }
        }catch (ExitException e) {
            menu = null;
        }
        return menu;
    }
}
