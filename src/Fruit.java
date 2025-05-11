public class Fruit extends Food{
    public int pacifyValue;

    public Fruit(int pacifyValue){
        this.pacifyValue = pacifyValue;
        setPrice(20);
    }

    public void use(Creature creature, Inventory inventory){
        if (inventory.upgradedWithFruit(creature)) {// eftersom metoden upgradedWithFruit kräver creature som parameter{
            creature.feed();
            System.out.println(creature.getCreatureName() + "has been upgraded to " + creature.getCreatureLevel());
        }
    }
}


}

