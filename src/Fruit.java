public class Fruit extends Food{

    public Fruit(double price){
        super(price);
    }

    @Override
    // method takes in target that is an object
    public void useToUpgrade(Object target, Inventory inventory){
        // makessure that target is an object that is a creature here
        if(target instanceof Creature creature) {
            if (inventory.canUpgradeWithFruit(creature)) {
                System.out.println(creature.getCreatureName() + " has been upgraded to level " + (creature.getCreatureLevel()+1) +" using "+creature.getFoodRequired()+" fruit.");
                Habitat habitat = creature.getHabitat();
                creature.upgrade(habitat);
            }
        }
    }

    @Override
    public void useToFeed(Creature creature, Inventory inventory){
        // Checks if creature can be pacified
        if (creature.possibleToPacify()){
            // checks if there is enough fruit to feed
            if(inventory.canFeedWithFruit(creature)){
                System.out.println(creature.fruitNeededToFeed()+" fruit used to pacify "+ creature.getCreatureName()+". Pacified level increased to level "+ (creature.getPacifyLevel()+1)+ ".");
                System.out.println(creature.getCreatureName()+" is easier for your guards to handle now...");
                creature.pacify(); // pacifies creature, pacifylevel increases

            }
        }
    }
}


