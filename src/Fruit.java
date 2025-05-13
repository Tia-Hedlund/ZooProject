public class Fruit extends Food{
    public int pacifyValue;


    public Fruit(int pacifyValue){
        this.pacifyValue = pacifyValue;
        //setPrice(20);
    }

    @Override
    // krävs att en av parametrarna är object target eftersom det anges i grunden för use metoden i items
    public void useToUpgrade(Object target, Inventory inventory){
        // om targeten är av typen creature så kan det genomföras
        if(target instanceof Creature creature) {
            if (inventory.upgradedWithFruit(creature)) {// eftersom metoden upgradedWithFruit kräver creature som parameter{
                creature.upgrade();
                System.out.println(creature.getCreatureName() + "has been upgraded to " + creature.getCreatureLevel());
            }
        }
    }

    public void pacify(){

    }
    @Override
    public void useToFeed(Creature creature, Inventory inventory){
        // Checkar om det går att pacifya med metoden possibleToPacify från creature klassen
        if (creature.possibleToPacify()){
            // checkar om det går att mata med frukt, om det finns i inventory
            if(inventory.fedWithFruit()){
                creature.Pacify(); // pacifyar, PacifyLevel ökar hos creaturen
            }
        }
    }
}


