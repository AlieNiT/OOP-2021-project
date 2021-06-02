package view.menu;

import controller.SignupController;
import view.menu.exceptions.BackException;
import view.menu.exceptions.ExitException;

public class SignupMenu extends Menu{
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
                return new MainMenu(controller.makeUser(userName, controller.checkPassUserFormat(getExactCommand("PASSWORD:"),"password")));
            else
                throw new Exception("The username already exists.");
        } catch (BackException e){ return new StartMenu();
        } catch (ExitException e){ return null;
        } catch (Exception e){ System.out.println(e.getMessage());
        }
        return menu;
    }

}
