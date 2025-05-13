public abstract class Item extends Buyable{

    public Item(double price) {
        super(price);
    }

    /*

     */
    public abstract void useToUpgrade(Object target, Inventory inventory);



}
