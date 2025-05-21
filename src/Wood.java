public class Wood extends Item{
    public Wood(double price){
        super(price);

    }


    @Override
    // one of the parameters of useToUpgrade is target of type method
    public void useToUpgrade(Object target, Inventory inventory){
        // If target is of the type habitat then the if statement will run cause (...) is true
        if(target instanceof Habitat habitat) {
            // if the method canUpgradeWithWood in inventory is true...
            if (inventory.canUpgradeWithWood(habitat)) {
                inventory.getItems().put("wood", inventory.getItems().getOrDefault("wood",0)-habitat.getIntWoodNeeded());
                // reduces the storage used according to the wood used.
                inventory.setStorageUsed(inventory.getStorageUsed()-habitat.getIntWoodNeeded()) ;
                // calls the upgrade method of the habitat
                System.out.println(habitat.getHabitatName() + " has been upgraded to level " + (habitat.getHabitatLevel()+1) +" using "+habitat.getIntWoodNeeded()+" wood.");
                habitat.upgrade();
            }
        }
    }
}
