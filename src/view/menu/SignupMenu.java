package view.menu;

import controller.menu.SignupController;
import view.menu.exceptions.BackException;
import view.menu.exceptions.ExitException;
import view.menu.exceptions.GameErrorException;

import static view.menu.color.Colors.colorPrintln;

public class SignupMenu extends Menu {
    SignupController controller;
    Menu menu = null;
    SignupMenu(SignupController controller){
        this.controller = controller;
    }
    public Menu run() {
        Menu menu = this;
        try {
            String userName = controller.checkPassUserFormat(getCommand("USERNAME:"),"username");
            if (controller.getUser(userName)==null)
                return new MainMenu(controller.makeUser(userName,
                        controller.checkPassUserFormat(getExactCommand("PASSWORD:"),"password")));
            else
                throw new Exception("This username already exists.");
        } catch (BackException e){ return new StartMenu();
        } catch (ExitException e) { return null;
        } catch (GameErrorException e) { colorPrintln(e.getMessage());
        } catch (Exception e){ e.printStackTrace();
        }
        return menu;
    }

}
