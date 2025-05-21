public class Wood extends Item{
    public Wood(double price){
        super(price);

    }
    @Override
    // krävs att en av parametrarna är object target eftersom det anges i grunden för use metoden i items
    public void useToUpgrade(Object target, Inventory inventory){
        // Of the target is of the type habitat the first if statement will be true
        if(target instanceof Habitat habitat) {
            // if the method canUpgradeWithWood in inventory is true...
            if (inventory.canUpgradeWithWood(habitat)) {
                inventory.getItems().put("wood", inventory.getItems().getOrDefault("wood",0)-habitat.getIntWoodNeeded());
                // reduces the storage used according to the wood used.
                inventory.setStorageUsed(inventory.getStorageUsed()-habitat.getIntWoodNeeded()) ;
                // calls the upgrade method of the habitat
                System.out.println(habitat.getHabitatName() + " has been upgraded to " + (habitat.getHabitatLevel()+1) +" using "+habitat.getIntWoodNeeded()+" wood.");
                habitat.upgrade();
            }
        }
    }
}
