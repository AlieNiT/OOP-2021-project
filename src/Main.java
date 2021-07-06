import controller.mission.Log;
import model.database.FileManager;
import view.menu.Menu;
import view.menu.StartMenu;
import java.io.IOException;
import static changes.Sound.backgroundMusic;

public class Main {
    public static void main(String[] args) throws IOException {
        FileManager.readMissions();
        FileManager.readUserBase();
        Log.setLogger("log.txt");
        Log.logger.info("Game started.");
        backgroundMusic();
        Menu currentMenu = new StartMenu();
        while (currentMenu != null) currentMenu = currentMenu.run();
    }
}
