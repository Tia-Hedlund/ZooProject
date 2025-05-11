public class Inventory {
    public int fishCount;
    public int fruitCount;
    public int woodCount;
    public int guards;
    public int storageUsed;

    public Inventory(int fishCount, int fruitCount, int woodCount, int storageUsed){
        this.fishCount = fishCount;
        this.fruitCount = fruitCount;
        this.woodCount = woodCount;
        this.storageUsed = storageUsed;
    }

    public int getFoodCount() {
        return fishCount+fruitCount;
    }

    public boolean upgradedWithFish(Creature creature){
        if (fishCount >= creature.getCreatureLevel()){
            fishCount = fishCount-creature.getCreatureLevel();
            storageUsed = storageUsed-creature.getCreatureLevel();
            return true; // det var möjligt att upgradera
        }
        else{
            System.out.println("Not enough fish in inventory to upgrade creature");
            return false; // det var ej möjligt att uppgradera
        }
    }

    public boolean upgradedWithFruit(Creature creature) {
        if (fruitCount >= creature.getCreatureLevel()) {
            fruitCount = fruitCount - creature.getCreatureLevel();
            storageUsed = storageUsed - creature.getCreatureLevel();
            return true; // det var möjligt att uppgradera;
        } else {
            System.out.println("Not enough fruit in inventory to upgrade creature");
            return false; // det var ej möjligt att uppgradera
        }
    }

    public boolean upgradedWithWood(Habitat habitat){
        if (woodCount>=habitat.getHabitatLevel()){
            woodCount = woodCount-habitat.getHabitatLevel();
            storageUsed = storageUsed-habitat.getHabitatLevel();
            return true; // det var möjligt att uppgradera;
        }
        else{
            System.out.println("Not enough wood in inventory to upgrade habitat");
            return false; // det var ej möjligt att uppgradera
        }

    }

    public boolean fedWithFish(){
        if (fishCount >0){
            fishCount--;
            storageUsed--;
            return true; // lyckades använda fisk för feed för det fanns tillräckligt
        }
        else{
            System.out.println("You dont have any fish in your inventory.");
            return false; // kunde inte använda fisk för feed för det fanns inte tillräckligt
        }
    }

    public boolean fedWithFruit(){
        if (fruitCount >0){
            fruitCount--;
            storageUsed--;
            return true; // lyckades använda fisk för feed för det fanns tillräckligt
        }
        else{
            System.out.println("You dont have any fruit in your inventory.");
            return false; // kunde inte använda fisk för feed för det fanns inte tillräckligt
        }
    }

}



