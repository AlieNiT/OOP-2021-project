package view.menu;

import controller.SignupController;
import model.play.database.User;

public class SignupMenu extends Menu{
    SignupController controller;
    Menu menu = null;
    @Override
    SignupMenu(SignupController controller){
        this.controller = controller;
    }


    public Menu run() {
        try {
            User user = controller.getUser(getCommand("USERNAME:"));
            if (user.getPassWord().equals(getCommand("PASSWORD:")))
                menu = new MainMenu(user);
            else throw new Exception("WRONG PASSWORD");
        } catch (Exception e){System.out.println(e.getMessage());}
        return menu;
    }

}
