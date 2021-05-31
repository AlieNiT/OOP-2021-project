package model.game.workshops.primaryworkshop;

import model.game.products.processedproducts.PocketMilk;
import model.game.products.processedproducts.ProcessedProduct;
import model.game.warehouseandtransportation.Savable;
import model.game.warehouseandtransportation.Warehouse;
//basteh bandi shir
//۳ .كارگاه بسته بندي شير : ورودي اين بخش شير بدست آمده از بوفالو و خروجي آن شير پاكتي است.
//• هزينه ساخت: ۴۰۰ سكه
//• مدت زمان لازم براي توليد شير پاكتي: ۶ واحد زماني
public class MilkPackagingWorkshop extends PrimaryWorkshop {

    public ProcessedProduct produce() {
        return new PocketMilk();
    }

    @Override
    public boolean consume(Warehouse warehouse) throws Exception {
        warehouse.hasSavable(Savable.MILK.name(),1);
        warehouse.removeSavable(Savable.MILK,1);
        return true;
    }
}
