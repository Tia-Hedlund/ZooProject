public class Fruit extends Food{
    public int pacifyValue;

    public Fruit(int pacifyValue){
        this.pacifyValue = pacifyValue;
        setPrice(20);
    }

    // krävs att en av parametrarna är object target eftersom det anges i grunden för use metoden i items
    public void use(Object target, Inventory inventory){
        // om targeten är av typen creature så kan det genomföras
        if(target instanceof Creature creature) {
            if (inventory.upgradedWithFruit(creature)) {// eftersom metoden upgradedWithFruit kräver creature som parameter{
                creature.feed();
                System.out.println(creature.getCreatureName() + "has been upgraded to " + creature.getCreatureLevel());
            }
        }
    }
}


