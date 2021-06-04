package view.menu;

import changes.Colors;
import view.menu.exceptions.BackException;
import view.menu.exceptions.ExitException;
import java.util.Scanner;

public abstract class Menu {
    protected static Scanner INPUT = new Scanner(System.in);
    public abstract Menu run();
    protected String getCommand(String string){
        System.out.println(Colors.randomColor(string));
        System.out.print(Colors.randomColor(">"));
        String response = INPUT.nextLine().toLowerCase().replaceAll("( )+", " ").trim();
        switch (response) {
            case "exit" -> throw new ExitException();
            case "back" -> throw new BackException();
            default -> { return response; }
        }
    }
    protected String getExactCommand(String string) {
        System.out.println(Colors.randomColor(string));
        System.out.print(Colors.randomColor(">"));
        String response = INPUT.nextLine();
        switch (response.toLowerCase().replaceAll("( )+", " ").trim()){
            case "exit" -> throw new ExitException();
            case "back" -> throw new BackException();
            default -> { return response; }
        }
    }
}
