import controller.mission.Log;
import model.database.FileManager;
import view.menu.Menu;
import view.menu.StartMenu;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FileManager.readMissions();
        FileManager.readUserBase();
        Log.setLogger("log.txt");
        Menu currentMenu = new StartMenu();
        while (currentMenu != null) currentMenu = currentMenu.run();
    }
}
