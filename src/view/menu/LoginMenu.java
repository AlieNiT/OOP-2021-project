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
        Menu menu = this;
        try {
            User user = controller.getUser(controller.checkPassUserFormat(getExactCommand("USERNAME:"),"username"));
            while (user != null) {
                if (user.getPassWord().equals(getExactCommand("PASSWORD:")))
                    return new MainMenu(user);
                System.out.println("WRONG PASSWORD");
            }
            throw new Exception("WRONG USERNAME");
        } catch (BackException e){ return new StartMenu();
        } catch (ExitException e){ return null;
        } catch (Exception e)    { System.out.println(e.getMessage());
        }
        return menu;
    }
}
