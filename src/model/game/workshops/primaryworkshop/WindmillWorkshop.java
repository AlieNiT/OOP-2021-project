package model.game.workshops.primaryworkshop;

import model.game.products.processedproducts.Flour;
import model.game.products.processedproducts.ProcessedProduct;
import model.game.warehouseandtransportation.Savable;
import model.game.warehouseandtransportation.Warehouse;

//۱ .آسياب : وظيفه آسياب توليد آرد است. كارگران آسياب با استفاده از تخم مرغ، آرد توليد مي كنند!
//• هزينه ساخت آسياب: ۱۵۰ سكه
//• مدت زمان لازم براي توليد آرد: ۴ واحد زماني
public class WindmillWorkshop extends PrimaryWorkshop {
    @Override
    public ProcessedProduct produce() {
        return new Flour();
    }

    @Override
    public boolean consume(Warehouse warehouse) throws Exception {
        warehouse.hasSavable(Savable.EGG,1);
        warehouse.removeSavable(Savable.EGG,1);
        return true;
    }
}
