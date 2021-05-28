package view.menu;

import controller.LoginController;
import model.play.database.User;

public class LoginMenu extends Menu {
    LoginController controller;
    public LoginMenu(LoginController controller){
        this.controller = controller;
    }
    public Menu run() {
        Menu menu = null;
        try {
            User user = controller.getUser(getCommand("USERNAME:"));
            if (user != null)
                menu = new MainMenu(user);
            else throw new Exception("WRONG USERNAME");
        } catch (Exception e){System.out.println(e.getMessage());}
        return menu;
    }
}
