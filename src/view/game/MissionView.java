package view.game;

import controller.mission.MissionController;
import model.database.Database;
import view.menu.MainMenu;
import view.menu.Menu;
import view.menu.exceptions.BackException;
import view.menu.exceptions.ExitException;
import view.menu.exceptions.GameErrorException;

import static view.menu.color.Colors.colorPrintln;

public class MissionView extends Menu {
    MissionController controller;

    public MissionView(MissionController controller) {
        this.controller = controller;
    }

    @Override
    public Menu run() {
        try {
            controller.runCommand(getCommand("Enter your command:"));

        } catch (BackException e){ return new MainMenu(controller.getUser());
        } catch (ExitException e) { return null;
        } catch (GameErrorException e) {
            if (e.getMessage().matches("You won in (\\d+) time units!")){
                colorPrintln(e.getMessage());
                return new MainMenu(controller.getUser());
            }
            else colorPrintln(e.getMessage());
        } catch (Exception e){ e.printStackTrace();
        }
        return this;
    }

}
