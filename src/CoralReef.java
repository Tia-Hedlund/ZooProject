import java.util.Random;
import java.util.Scanner;

public class CoralReef extends OceanHabitat{


    public CoralReef(double price, String habitatName, int habitatLevel, int creatureLevelLimit, Biome habitatBiome, int totalLevelInHabitat) {
        super(price, habitatName, habitatLevel, creatureLevelLimit, habitatBiome, totalLevelInHabitat);
    }

    /*
    public void tryGenerateFish(Habitat habitat, Inventory inventory, Zoo zoo){
        Random myrandom = new Random();

        int randomBound;

        if(10-habitat.getHabitatLevel()>1){
            randomBound=10-habitat.getHabitatLevel();
        }
        else{
            randomBound=1;
        }

        // the more a player upgrades their habitat, the likelier they are to receive items from habitat.
        int randomInt = myrandom.nextInt(randomBound);

        // if the randomized int is equal to 1, the Zoo will receive a fish that will be added to inventory
        if (randomInt == 1){

            randomInt = myrandom.nextInt(2)+1;
            int itemsRecieved = randomInt*2;
            System.out.println(zoo.getName() +" recieved fish from the "+ habitat.getHabitatName()+ ".");
            System.out.println(itemsRecieved+" Fish added to inventory.");
            generateFish(inventory, itemsRecieved);
        }
    }

    public void generateFish(Inventory inventory, int itemsRecieved){
        // gets the previous amount of fish in inventory from the Hashmap in inventory
        int fishInInventory = inventory.getItems().getOrDefault("fish",0);
        // adds 1
        inventory.getItems().put("fish", fishInInventory + itemsRecieved);
    }

     */

    @Override
    public void tryGenerateItem(Inventory inventory, Zoo zoo){
        Random myrandom = new Random();

        if (inventory.getStorageUsed()>=zoo.getMaxStorage()){
            System.out.println("Storage is full. Could not collect fish from " + getHabitatName() + ". ");
            return;
        }

        double baseDropRate = 0.2 + (getHabitatLevel() *0.05);
        double randomDouble = myrandom.nextDouble();

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

