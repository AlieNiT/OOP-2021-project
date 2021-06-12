package model.game.products.processedproducts;

import controller.mission.time.TimeManager;
import model.game.products.Product;

public abstract class ProcessedProduct extends Product {
    public ProcessedProduct(TimeManager timeManager) {
        super(timeManager);
    }
}
