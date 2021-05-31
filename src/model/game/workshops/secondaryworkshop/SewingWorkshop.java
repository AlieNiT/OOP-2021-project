package model.game.workshops.secondaryworkshop;

import model.game.products.finalproducts.FinalProduct;
import model.game.products.finalproducts.Shirt;
import model.game.warehouseandtransportation.Savable;
import model.game.warehouseandtransportation.Warehouse;

//khayati
//۲ .خياطي : پارچه هاي بافته شده در اينجا به پيراهن تبديل مي شوند.
//• هزينه ساخت: ۴۰۰ سكه
//• مدت فرايند: ۶ واحد زماني
public class SewingWorkshop extends SecondaryWorkshop {
    @Override
    public FinalProduct produce() {
        return new Shirt();
    }

    @Override
    public boolean consume(Warehouse warehouse) throws Exception {
        warehouse.hasSavable(Savable.CLOTH.name(),1);
        warehouse.removeSavable(Savable.CLOTH,1);
        return true;
    }
}
