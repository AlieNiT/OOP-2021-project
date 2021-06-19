package view.menu;

import controller.menu.LoginController;
import controller.menu.SignupController;
import view.menu.exceptions.BackException;
import view.menu.exceptions.ExitException;

import static model.database.FileManager.emptyLog;
import static view.menu.color.Colors.colorPrintln;

public class StartMenu extends Menu{
    @Override
    public Menu run() {
        emptyLog();
        Menu menu = this;
        try {
            switch (getCommand("(start menu)LOGIN/SIGNUP:")) {
                case "login" -> menu = new LoginMenu(new LoginController());
                case "signup" -> menu = new SignupMenu(new SignupController());
                default -> colorPrintln("Invalid command");
            }
        } catch (ExitException | BackException e) { menu = null; }
        return menu;
    }
}
