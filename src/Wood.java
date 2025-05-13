public class Wood extends Item{
    public Wood(double price){
        super(price);

    }
    @Override
    // krävs att en av parametrarna är object target eftersom det anges i grunden för use metoden i items
    public void useToUpgrade(Object target, Inventory inventory){
        // om targeten är av typen habitat så kan det genomföras
        if(target instanceof Habitat habitat) {
            if (inventory.upgradedWithWood(habitat)) {// eftersom metoden upgradedWithFruit kräver creature som parameter{
                habitat.upgrade();
                System.out.println(habitat.getHabitatName() + "has been upgraded to " + habitat.getHabitatLevel());
            }
        }
    }
}
