package view.menu;

import controller.menu.MainController;
import model.database.User;
import view.menu.exceptions.BackException;
import view.menu.exceptions.ExitException;
import view.menu.exceptions.GameErrorException;

import static view.menu.color.Colors.*;

public class MainMenu extends Menu {
    MainController controller;
    boolean shown = false;

    public MainMenu(User user) {
        controller = new MainController(user);
    }

    @Override
    public Menu run() {
        Menu menu = this;
        if (!shown) {
            colorPrint("           ⚜  \u001b[38;5;220m⚜️");
            reverseColor();
            colorPrint("FARM FRENZY  \u001b[38;5;220m⚜️");
            reverseColor();
            colorPrintln("⚜\nSTART        SETTINGS        LOGOUT        EXIT");
        }
        shown = false;
        try {
            return switch (getCommand(null)) {
                case "start" -> menu = controller.chooseMission(getCommand("Choose a level (1-" + controller.user.getCurrentMission() + ")"));
                case "settings" -> menu = new SettingsMenu();
                case "logout" -> menu = new StartMenu();
                case "exit" -> menu = null;
                default -> throw new GameErrorException("Invalid command");
            };
        } catch (BackException e) { menu = new StartMenu();
        } catch (ExitException e) { menu = null;
        } catch (GameErrorException e) { colorPrintln(e.getMessage());
        } catch (Exception e) { e.printStackTrace();
        }
        return menu;
    }
}
