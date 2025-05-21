public class Fish extends Food{
    private double goldBonusValue = 0.1;

    public Fish(double price) {
        super(price);
    }


    public double getGoldBonusValue() {
        return goldBonusValue;
    }

    @Override
    // One of the parameters is object target because the method for use in items requires a target
    public void useToUpgrade(Object target, Inventory inventory){
        // the target is here checked if it is a type of creature
        if(target instanceof Creature creature){
            // if it is then...
            if (inventory.canUpgradeWithFish(creature)){
                System.out.println(creature.getCreatureName() + " has been upgraded to level " + (creature.getCreatureLevel()+1) +" using "+creature.getFoodRequired()+" fish.");
                // gets the creatures habitat because creatures method upgrades nneesd it as paramter
                Habitat habitat = creature.getHabitat();
                creature.upgrade(habitat);
            }
        }
    }

    // overrides the method use to feed that all food has
    @Override
    public void useToFeed(Creature creature, Inventory inventory){
        // if canFeedWithFish returns true, method in inventory, then...
        if (inventory.canFeedWithFish(creature)){
            System.out.println(creature.fishNeededToFeed()+" fish used to increase "+ creature.getCreatureName()+"s Gold Boost to "+ (creature.getCreatureGoldBonus()+0.1)+ ".");
            // the creature gets fed and goldbonus updated
            creature.updateGoldBonus(this);
        }
    }
}

