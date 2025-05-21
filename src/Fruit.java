public class Fruit extends Food{

    public Fruit(double price){
        super(price);
    }

    @Override
    // krävs att en av parametrarna är object target eftersom det anges i grunden för use metoden i items
    public void useToUpgrade(Object target, Inventory inventory){
        // om targeten är av typen creature så kan det genomföras
        if(target instanceof Creature creature) {
            if (inventory.canUpgradeWithFruit(creature)) {// eftersom metoden upgradedWithFruit kräver creature som parameter{
                System.out.println(creature.getCreatureName() + " has been upgraded to level " + (creature.getCreatureLevel()+1) +" using "+creature.getFoodRequired()+" fruit.");
                Habitat habitat = creature.getHabitat();
                creature.upgrade(habitat);
            }
        }
    }

    @Override
    public void useToFeed(Creature creature, Inventory inventory){
        // Checkar om det går att pacifya med metoden possibleToPacify från creature klassen
        if (creature.possibleToPacify()){
            // checkar om det går att mata med frukt, om det finns i inventory
            if(inventory.canFeedWithFruit(creature)){
                System.out.println(creature.fruitNeededToFeed()+" fruit used to pacify "+ creature.getCreatureName()+". Pacified level increased to level "+ (creature.getPacifyLevel()+1)+ ".");
                System.out.println(creature.getCreatureName()+" is easier for your guards to handle now...");
                creature.pacify(); // pacifyar, PacifyLevel ökar hos creaturen

            }
        }
    }
}


