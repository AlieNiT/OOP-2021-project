package view.menu;

import view.menu.exceptions.BackException;
import view.menu.exceptions.ExitException;

import java.util.Scanner;

import static view.menu.color.Colors.colorPrint;
import static view.menu.color.Colors.colorPrintln;

public abstract class Menu {
    protected static Scanner INPUT = new Scanner(System.in);
    public abstract Menu run();
    protected String getCommand(String string){
        if (string != null)
            colorPrintln(string);
        colorPrint(">");
        String response = INPUT.nextLine().toLowerCase().replaceAll("( )+", " ").trim();
        switch (response) {
            case "exit" -> throw new ExitException();
            case "back" -> throw new BackException();
            default -> { return response; }
        }
    }
    protected String getExactCommand(String string) {
        colorPrintln(string);
        colorPrint(">");
        String response = INPUT.nextLine();
        switch (response.toLowerCase().replaceAll("( )+", " ").trim()){
            case "exit" -> throw new ExitException();
            case "back" -> throw new BackException();
            default -> { return response; }
        }
    }
}
