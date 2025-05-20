public class Guard extends Buyable{

    public Guard(double price) {
        super(price);
    }

    public double getTotalPrice(Zoo zoo){
        return price*(zoo.getGuardCount()+1);
    }

}
