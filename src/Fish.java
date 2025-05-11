public class Fish extends Food{
    public int goldBonusValue;

    public Fish(int goldBonusValue){
        this.goldBonusValue = goldBonusValue;
        setPrice(10);
    }

    public void use(Creature creature, Inventory inventory){
        if (inventory.upgradedWithFish(creature)){// eftersom metoden upgradedWithFish kräver creature som parameter{
            creature.feed();
            System.out.println(creature.getCreatureName() + "has been upgraded to " + creature.getCreatureLevel());
        }
    }
}

