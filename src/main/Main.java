package main;

import view.menu.LoginMenu;
import view.menu.Menu;
import view.menu.StartMenu;

public class Main {

    public static void main(String[] args) {
        Menu currentMenu = new StartMenu();
        while (currentMenu!=null){
            currentMenu = currentMenu.run();
        }
    }
}
