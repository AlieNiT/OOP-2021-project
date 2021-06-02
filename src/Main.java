import model.database.FileManager;
import view.menu.LoginMenu;
import view.menu.Menu;
import view.menu.StartMenu;

import java.io.File;
import java.io.FileNotFoundException;


public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        FileManager.readUserBase();
        Menu currentMenu = new StartMenu();
        while (currentMenu!=null){
            currentMenu = currentMenu.run();
        }
    }
}
