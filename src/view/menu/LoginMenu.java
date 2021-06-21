package view.menu;

import controller.menu.LoginController;
import controller.mission.Log;
import model.database.User;
import view.menu.exceptions.BackException;
import view.menu.exceptions.ExitException;
import view.menu.exceptions.GameErrorException;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static view.menu.color.Colors.colorPrint;
import static view.menu.color.Colors.colorPrintln;

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
            if (user == null) colorPrintln("WRONG USERNAME");
            while (user != null) {
                if (user.getPassword().equals(getExactCommand("PASSWORD:"))) {
                    Log.logger.info("User " + user.getUsername() + " logged in.");
                    return new MainMenu(user);
                }
                tries--;
                if (tries != 0) {
                    colorPrintln("WRONG PASSWORD. TRY AGAIN:");
                }
                if (tries == 0) {
                    colorPrintln("WRONG PASSWORD.");
                    tries = 3;
                    Log.logger.warning("⚠ Suspicious unsuccessful login attempt to account " + user.getUsername());
                    for (int i = 10; i > 0; i--) {
                        colorPrint("Out of attempts. Try again in " + i);
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
        } catch (ExitException e) { return null;
        } catch (GameErrorException e) { colorPrintln(e.getMessage());
        } catch (Exception ignored) { }
        return menu;
    }
}
