package view.menu;

import java.util.Scanner;

public abstract class Menu {
    protected static Scanner INPUT = new Scanner(System.in);
    public abstract Menu run();
    Menu menu = null;
    protected String getCommand(String input) {
        System.out.println(input);
        return INPUT.nextLine().trim();
    }}
