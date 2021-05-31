package model.game.workshops.secondaryworkshop;

import model.game.products.finalproducts.Bread;
import model.game.products.finalproducts.FinalProduct;
import model.game.warehouseandtransportation.Savable;
import model.game.warehouseandtransportation.Warehouse;

//nanvayi
//۱ .نانوايي : ورودي نانوايي آرد است و پس از پخت و پز در خروجي به شما نان تحويل داده مي شود.
//• هزينه ساخت نانوايي: ۲۵۰ سكه
//• مدت زمان لازم براي فرآيند پخت: ۵ واحد زماني
public class Bakery extends SecondaryWorkshop {

    @Override
    public FinalProduct produce() {
        return new Bread();
    }


    @Override
    public boolean consume(Warehouse warehouse) throws Exception {
        warehouse.hasSavable(Savable.FLAVOR.name(),1);
        warehouse.removeSavable(Savable.FLAVOR,1);
        return true;
    }
}
