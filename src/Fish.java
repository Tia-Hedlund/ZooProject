public class Fish extends Food{
    private double goldBonusValue = 0.1;

    public Fish(double price) {
        super(price);
    }


    public double getGoldBonusValue() {
        return goldBonusValue;
    }

    @Override
    // krävs att en av parametrarna är object target eftersom det anges i grunden för use metoden i items
    public void useToUpgrade(Object target, Inventory inventory){
        // om targeten är av typen creature så kan det genomföras
        if(target instanceof Creature creature){
            if (inventory.canUpgradeWithFish(creature)){// eftersom metoden upgradedWithFish kräver creature som parameter{
                creature.upgrade();
                System.out.println(creature.getCreatureName() + "has been upgraded to " + creature.getCreatureLevel());
            }
        }
    }

    @Override
    public void useToFeed(Creature creature, Inventory inventory){
        if (inventory.fedWithFish()){
            creature.updateGoldBonus(this);
        }
    }
}

