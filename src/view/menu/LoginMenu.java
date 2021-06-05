package view.menu;

import controller.menu.LoginController;
import model.database.User;
import view.menu.exceptions.BackException;
import view.menu.exceptions.ExitException;
import java.util.concurrent.TimeUnit;
import static changes.Colors.colorPrintln;
import static changes.Colors.colorPrint;

public class LoginMenu extends Menu {
    LoginController controller;
    public LoginMenu(LoginController controller){
        this.controller = controller;
    }
    public Menu run() {
        Menu menu = this;
        try {
            User user = controller.getUser(controller.checkPassUserFormat(getExactCommand("USERNAME:"),"username"));
            int tries = 3;
            while (user != null) {
                if (user.getPassWord().equals(getExactCommand("PASSWORD:")))
                    return new MainMenu(user);
                tries--;
                if (tries != 0) {
                    colorPrintln("WRONG PASSWORD. TRY AGAIN:");
                }
                if (tries == 0) {
                    colorPrintln("WRONG PASSWORD.");
                    tries = 3;
                    for (int i = 10; i > 0; i--) {
                        colorPrint("Out of attempts. Try again in " + String.valueOf(i));
                        for (int j = 0; j < 3; j++) {
                            colorPrint(".");
                            TimeUnit.MILLISECONDS.sleep(333);
                        }
                        System.out.print("\r");
                    }
                    System.out.print("\r");
                    colorPrintln("TRY AGAIN:");
                }
            }
            throw new Exception("WRONG USERNAME");
        } catch (BackException e){ return new StartMenu();
        } catch (ExitException e){ return null;
        } catch (Exception e) { colorPrintln(e.getMessage()); }
        return menu;
    }
}
