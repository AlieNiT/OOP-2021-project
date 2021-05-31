package model.game.workshops.secondaryworkshop;

import model.game.products.finalproducts.FinalProduct;
import model.game.products.finalproducts.IceCream;
import model.game.warehouseandtransportation.Savable;
import model.game.warehouseandtransportation.Warehouse;

//bastaniforooshi
//۳ .بستني فروشي : در اين كارگاه از شيرپاكتي توليد شده بستني توليد مي شود.
//• هزينه ساخت: ۵۵۰ سكه
//• مدت زمان فرايند: ۷ واحد زماني
public class IceCreamWorkshop extends SecondaryWorkshop {
    @Override
    public FinalProduct produce() {
        return new IceCream();
    }

    @Override
    public boolean consume(Warehouse warehouse) throws Exception {
        warehouse.hasSavable(Savable.POCKET_MILK.name(),1);
        warehouse.removeSavable(Savable.POCKET_MILK,1);
        return true;
    }
}
