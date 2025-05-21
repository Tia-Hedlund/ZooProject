public abstract class Item extends Buyable{

    // constructor
    public Item(double price) {
        super(price);
    }

    // creates the method that gets overiden
    public abstract void useToUpgrade(Object target, Inventory inventory);



}
