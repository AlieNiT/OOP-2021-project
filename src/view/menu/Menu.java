package view.menu;

import view.menu.exceptions.BackException;
import view.menu.exceptions.ExitException;

import java.util.Scanner;

public abstract class Menu {
    protected static Scanner INPUT = new Scanner(System.in);
    public abstract Menu run();
    protected String getCommand(String input){
        System.out.println(input);
        String response = INPUT.nextLine().trim();
        switch (response){
            case "exit" -> throw new ExitException();
            case "back" -> throw new BackException();
            default -> { return response; }
        }
    }
}
