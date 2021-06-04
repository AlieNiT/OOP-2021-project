package view.menu;

import changes.Colors;
import controller.LoginController;
import controller.SignupController;
import view.menu.exceptions.ExitException;

public class StartMenu extends Menu{
    @Override
    public Menu run() {
        Menu menu = this;
        try {
            switch (getCommand("(start menu)LOGIN/SIGNUP:")) {
                case "login" -> menu = new LoginMenu(new LoginController());
                case "signup" -> menu = new SignupMenu(new SignupController());
                default -> System.out.println(Colors.randomColor("Invalid command"));
            }
        } catch (ExitException e) { menu = null; }
        return menu;
    }
}
