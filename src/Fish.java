public class Fish extends Food{
    public int goldBonusValue;

    public Fish(int goldBonusValue){
        this.goldBonusValue = goldBonusValue;
        setPrice(10);
    }

    public void use(Creature creature, Inventory inventory){

    }

}

