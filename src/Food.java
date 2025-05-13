public abstract class Food extends Item{

    public Food(double price) {
        super(price);
    }

    public abstract void useToFeed(Creature creature, Inventory inventory);
}
