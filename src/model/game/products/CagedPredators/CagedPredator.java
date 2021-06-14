package model.game.products.CagedPredators;

import controller.mission.time.TimeManager;
import model.game.products.Product;

public class CagedPredator extends Product {
    public CagedPredator(TimeManager timeManager, int x, int y) {
        super(timeManager,5, x, y);
    }
}
