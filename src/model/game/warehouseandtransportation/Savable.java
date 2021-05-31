package model.game.warehouseandtransportation;

public enum Savable {
    EGG("egg",1,15),
    MILK("milk",1,25),
    FEATHER("feather",1,20),
    CLOTH("cloth",2,50),
    FLAVOR("flavor",2,40),
    POCKET_MILK("pocket milk",2,60),
    BREAD("bread",4,80),
    ICE_CREAM("ice cream",4,120),
    SHIRT("shirt",4,100),
    BEAR("bear",15,400),
    LION("lion",15,300),
    TIGER("tiger",15,500);

    int volume;
    String name;
    int price;

    Savable(String name, int volume,int price) {
        this.volume = volume;
        this.name = name;
        this.price = price;
    }
}
