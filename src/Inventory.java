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

    public void discardItem(){

    }
}


