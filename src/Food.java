public abstract class Food extends Item{

    // constructor
    public Food(double price) {
        super(price);
    }

    // creates the method that fish and fruit will override
    public abstract void useToFeed(Creature creature, Inventory inventory);
}
