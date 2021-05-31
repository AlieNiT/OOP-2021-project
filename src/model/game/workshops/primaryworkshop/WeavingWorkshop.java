package model.game.workshops.primaryworkshop;

import model.game.products.processedproducts.Cloth;
import model.game.products.processedproducts.ProcessedProduct;
import model.game.warehouseandtransportation.Savable;
import model.game.warehouseandtransportation.Warehouse;

//parcheh bafi
//۲ .پارچه بافي : در اين كارگاه پرهاي گرفته شده از بوقلمون به پارچه تبديل مي شوند.
//• هزينه ساخت: ۲۵۰ سكه
//• مدت زمان لازم براي توليد پارچه: ۵ واحد زماني
public class WeavingWorkshop extends PrimaryWorkshop {
    @Override
    public ProcessedProduct produce() {
        return new Cloth();
    }


    @Override
    public boolean consume(Warehouse warehouse) throws Exception {
        warehouse.hasSavable(Savable.FEATHER.name(),1);
        warehouse.removeSavable(Savable.FEATHER,1);
        return true;
    }
}
