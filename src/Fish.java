public class Fish extends Food{
    public int goldBonusValue;

    public Fish(int goldBonusValue){
        super(2);
        this.goldBonusValue = goldBonusValue;
        setPrice(10);
    }

    public void use(){

    }

}

