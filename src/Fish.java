public class Fish extends Food{
    public int goldBonusValue;

    public Fish(int goldBonusValue){
        this.goldBonusValue = goldBonusValue;
        setPrice(10);
    }

    // krävs att en av parametrarna är object target eftersom det anges i grunden för use metoden i items
    public void useToUpgrade(Object target, Inventory inventory){
        // om targeten är av typen creature så kan det genomföras
        if(target instanceof Creature creature){
            if (inventory.upgradedWithFish(creature)){// eftersom metoden upgradedWithFish kräver creature som parameter{
                creature.upgrade();
                System.out.println(creature.getCreatureName() + "has been upgraded to " + creature.getCreatureLevel());
            }

        }

    }

    public void useToFeed(Creature creature, Inventory inventory){

    }
}

