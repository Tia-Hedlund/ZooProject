import java.util.HashMap;

public class Inventory {

    public int woodCount; // gör om så jag kan ta bort denna
    private int storageUsed;
    private HashMap<String, Integer> items;

    public Inventory(int storageUsed){
        items = new HashMap<>();
        this.storageUsed = storageUsed;
    }

    public int getStorageUsed() {
        return storageUsed;
    }

    public void setStorageUsed(int storageUsed) {
        this.storageUsed = storageUsed;
    }

    public HashMap<String, Integer> getItems() {
        return items;
    }

    // method for increasing the Storage used with a specific quantity
    public void increaseStorageUsed(int quantity){
        this.storageUsed+=quantity;
    }

    public void decreaseStorageUsed(int quantity){
        this.storageUsed-=quantity;
    }

    public void increaseItem(String itemName, int quantity){
        items.put(itemName, items.getOrDefault(itemName,0) + quantity);
        increaseStorageUsed(quantity);
    }

    public void decreaseItem(String itemName, int quantity){
        items.put(itemName, items.getOrDefault(itemName,0) - quantity);
        decreaseStorageUsed(quantity);
    }

    public void printInventory(Zoo zoo){
        System.out.println("Storage used: ("+ storageUsed+"/"+zoo.getMaxStorage()+")");
        System.out.printf("%-15s %-15s %s\n", "Wood:", "Fish:", "Fruit:");
        System.out.printf("%-15s %-15s %s\n", items.getOrDefault("wood",0), items.getOrDefault("fish",0), items.getOrDefault("fruit",0));
        System.out.println();
    }

    public boolean canUpgradeWithFish(Creature creature){
        if (items.getOrDefault("fish", 0) >= creature.getFoodRequired()){
            // Updates key "fish" in the Hashmap, to store the difference between the previous amount of fish and the creature's level
            items.put("fish", items.get("fish") - creature.getFoodRequired());
            // Storage used is reduced by creatureLevel, the same amount of Fish that were used to Upgrade
            storageUsed -= creature.getFoodRequired();
            return true; // det var möjligt att upgradera
        }
        else{
            System.out.println("Not enough fish in inventory to upgrade "+creature.getCreatureName()+".");
            return false; // det var ej möjligt att uppgradera
        }
    }

    public boolean canUpgradeWithFruit(Creature creature) {
        if (items.getOrDefault("fruit", 0) >= creature.getFoodRequired()) {
            items.put("fruit", items.get("fruit") - creature.getFoodRequired());
            storageUsed -= creature.getFoodRequired();
            return true; // det var möjligt att uppgradera;
        } else {
            System.out.println("Not enough fruit in inventory to upgrade "+creature.getCreatureName()+".");
            return false; // det var ej möjligt att uppgradera
        }
    }

    public boolean canUpgradeWithWood(Habitat habitat){
        if (items.getOrDefault("wood", 0)>=habitat.getIntWoodNeeded()){
            return true; // Returns true, "yes-it can upgrade with wood"
        }
        else{
            System.out.println("Not enough wood to upgrade "+habitat.getHabitatName()+".");
            return false; // Returns false, could not upgrade with wood
        }
    }

    public boolean canFeedWithFish(Creature creature){
        if (items.getOrDefault("fish", 0) >= creature.fishNeededToFeed()){
            items.put("fish", items.getOrDefault("fish", 0)-creature.fishNeededToFeed());
            storageUsed-=creature.fishNeededToFeed();
            return true; // lyckades använda fisk för feed för det fanns tillräckligt
        }
        else if (items.getOrDefault("fish",0) == 0){
            System.out.println("You dont have any fish in your inventory.");
            return false; // kunde inte använda fisk för feed för det fanns inte tillräckligt
        }
        else{
            System.out.println("("+items.getOrDefault("fish", 0)+"/"+ creature.fishNeededToFeed()+") Not enough fish in inventory too increase the Gold Bonus of " + creature.getCreatureName()+".");

            return false;
        }
    }

    public boolean canFeedWithFruit(Creature creature){
        if (items.getOrDefault("fruit", 0) >=creature.fruitNeededToFeed()){
            items.put("fruit", items.getOrDefault("fruit", 0) -creature.fruitNeededToFeed());
            storageUsed-= creature.fruitNeededToFeed();
            return true; // lyckades använda fruit för feed för det fanns tillräckligt
        }
        else if (items.getOrDefault("fruit",0) == 0){
            System.out.println("You dont have any fruit in your inventory.");
            return false; // kunde inte använda fisk för feed för det fanns inte tillräckligt
        }
        else{
            System.out.println("("+items.getOrDefault("fruit", 0)+"/"+ creature.fruitNeededToFeed()+")Not enough fruit in inventory too pacify" + creature.getCreatureName()+".");
            return false;
        }
    }
}



