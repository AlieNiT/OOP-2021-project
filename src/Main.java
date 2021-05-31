import view.menu.LoginMenu;
import view.menu.Menu;
import view.menu.StartMenu;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList
        Menu currentMenu = new StartMenu();
        while (currentMenu!=null){
            currentMenu = currentMenu.run();
        }
    }
}
