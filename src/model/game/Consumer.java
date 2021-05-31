package model.game;
import model.game.warehouseandtransportation.Warehouse;

public interface Consumer {
    boolean consume(Warehouse warehouse) throws Exception;
}
