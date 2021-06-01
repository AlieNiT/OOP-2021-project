package view.menu;

import controller.LoginController;
import model.database.User;
import view.menu.exceptions.BackException;
import view.menu.exceptions.ExitException;

public class LoginMenu extends Menu {
    LoginController controller;
    public LoginMenu(LoginController controller){
        this.controller = controller;
    }
    public Menu run() {
        Menu menu = null;
        try {
            User user = controller.getUser(getCommand("USERNAME:"));
            while (user != null) {
                if (user.getPassWord().equals(getCommand("PASSWORD:")))
                    return new MainMenu(user);
                System.out.println("WRONG PASSWORD");
            }
            throw new Exception("WRONG USERNAME");
        } catch (BackException e){
            menu = new StartMenu();
        } catch (Exception e){

        }
        return menu;
    }
}
