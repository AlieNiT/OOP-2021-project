package controller.mission.time;

import model.Actioner;
import model.game.Producer;
import model.game.animals.farmanimals.FarmAnimal;
import model.game.products.Product;

public class Action {
    Actioner actioner = null;
    public Action(Product product) {
        actioner = product;
    }

    public Action(Producer producer){
        actioner = producer;
    }

    public Action(FarmAnimal farmAnimal) { actioner = farmAnimal;}

    public Action() {
        //for truck
    }

}
