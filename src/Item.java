public abstract class Item extends Buyable{

    /*
    public int getPrice(){
        return price;
    }

    // metod för att sätta priset

    public void setPrice(int price){
        this.price = price;
    }

    public abstract void buy();
    public abstract void sell();
    // use metoden kan kallas på för itemet när den behövs för att uppgradera ett djur eller
     */
    public abstract void useToUpgrade(Object target, Inventory inventory);



}
