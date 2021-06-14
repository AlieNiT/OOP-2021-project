package model.game.products.processedproducts;

import controller.mission.time.TimeManager;
import model.game.products.Product;

public abstract class ProcessedProduct extends Product {
    public ProcessedProduct(TimeManager timeManager, int x, int y) {
        super(timeManager,5, x, y);
    }
}
