import java.util.Random;
import java.util.Scanner;

public class CoralReef extends OceanHabitat{

    // constructor based on the class it extends
    public CoralReef(double price, String habitatName, int habitatLevel, int creatureLevelLimit, Biome habitatBiome, int totalLevelInHabitat) {
        super(price, habitatName, habitatLevel, creatureLevelLimit, habitatBiome, totalLevelInHabitat);
    }

    // overriders the polimorfi method that all Habitats have, but changes what the method does, from empty to generating an item
    @Override
    public void tryGenerateItem(Inventory inventory, Zoo zoo){
        // creates a random
        Random myrandom = new Random();

        // if statement to check if there is space in the storage or not
        if (inventory.getStorageUsed()>=zoo.getMaxStorage()){
            // if the storage was full, print to user that the item could not be collected
            System.out.println("Storage is full. Could not collect fish from " + getHabitatName() + ". ");
            return;
        }

        // if its not full, since the if statement returned and ended the whole method,
        // create a baseDropRate of the type double which is the drop rate of the item
        double baseDropRate = 0.2 + (getHabitatLevel() *0.05);
        // creates a random double between 0-1
        double randomDouble = myrandom.nextDouble();

        // if the random double is smaller than the drop rate... (therefore the percentage of the ifstatement happening is the droprate *100)
        if (randomDouble < baseDropRate){
            // create an int to set a maximum amount of wood that can be generated.
            int woodGenCap = getHabitatLevel();
            // Generates a random number between 1 and the maximum amount of wood that can be generated
            int quantity = 1+myrandom.nextInt(woodGenCap);

            int spaceInStorage = zoo.getMaxStorage()-inventory.getStorageUsed();
            // if the random quantity is bigger than the available space in storage, the smaller of the values will be chosen.
            // cant generate more than the available space.
            int allowedQuantity = Math.min(quantity, spaceInStorage);

            inventory.increaseItem("fish", allowedQuantity);
            System.out.println(allowedQuantity + " fish collected from " +getHabitatName()+ ". ");
        }
    }
}

