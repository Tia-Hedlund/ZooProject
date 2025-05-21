public class Guard extends Buyable{

    // construcotr
    public Guard(double price) {
        super(price);
    }

    // getter kid of to get the total price for guard in the shop
    public double getTotalPrice(Zoo zoo){
        return getPrice()*(zoo.getGuardCount()+1);
    }

}
