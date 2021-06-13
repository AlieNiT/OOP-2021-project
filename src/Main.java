import model.database.FileManager;
import view.menu.Menu;
import view.menu.StartMenu;

import java.io.FileNotFoundException;

import static model.database.FileManager.readUserBase;


public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        readUserBase();
        FileManager.readMissions();
        Menu currentMenu = new StartMenu();
        while (currentMenu!=null){
            currentMenu = currentMenu.run();
        }
    }
}
