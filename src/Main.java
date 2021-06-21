import controller.mission.Log;
import model.database.FileManager;
import view.menu.Menu;
import view.menu.StartMenu;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;

import static model.database.FileManager.readUserBase;

public class Main {
    public static void main(String[] args) throws IOException {
        Log.setLogger("log.txt");
        FileManager.readMissions();
        Menu currentMenu = new StartMenu();
        while (currentMenu != null){
            currentMenu = currentMenu.run();
        }
    }
}
