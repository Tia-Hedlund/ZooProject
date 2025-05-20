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

    public HashMap<String, Integer> getItems() {
        return items;
    }

    // method for increasing the Storage used with a specific quantity
    public void increaseStorageUsed(int quantity){
        this.storageUsed+=quantity;
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
            items.put("wood", items.getOrDefault("wood",0)-habitat.getIntWoodNeeded());
            // reduces the storage used according to the wood used.
            storageUsed -= habitat.getHabitatLevel();
            return true; // Returns true, "yes-it can upgrade with wood"
        }
        else{
            System.out.println("Not enough wood to upgrade "+habitat.getHabitatName()+".");
            return false; // Returns false, could not upgrade with wood
        }
    }

    public boolean fedWithFish(){
        if (items.getOrDefault("fish", 0) > 0){
            items.put("fish", items.getOrDefault("fish", 0)-1);
            storageUsed--;
            return true; // lyckades använda fisk för feed för det fanns tillräckligt
        }
        else{
            System.out.println("You dont have any fish in your inventory.");
            return false; // kunde inte använda fisk för feed för det fanns inte tillräckligt
        }
    }

    public boolean fedWithFruit(){
        if (items.getOrDefault("fruit", 0) >0){
            items.put("fruit", items.getOrDefault("fruit", 0) -1);
            storageUsed--;
            return true; // lyckades använda fruit för feed för det fanns tillräckligt
        }
        else{
            System.out.println("You dont have any fruit in your inventory.");
            return false; // kunde inte använda fruit för feed för det fanns inte tillräckligt
        }
    }
}



