import java.util.Random;
import java.util.Scanner;

public class Forest extends WoodlandHabitat{

    public Forest(double price, String habitatName, int habitatLevel, int creatureLevelLimit, Biome habitatBiome, int totalLevelInHabitat) {
        super(price, habitatName, habitatLevel, creatureLevelLimit, habitatBiome, totalLevelInHabitat);
    }

    @Override
    public void tryGenerateItem(Inventory inventory, Zoo zoo){
        Random myrandom = new Random();

        if (inventory.getStorageUsed()>=zoo.getMaxStorage()){
            System.out.println("Storage is full. Could not collect wood from " + getHabitatName() + ". ");
            return;
        }

        double baseDropRate = 0.25 + (getHabitatLevel() *0.05);
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

            inventory.increaseItem("wood", allowedQuantity);
            System.out.println(allowedQuantity + " wood collected from " +getHabitatName()+ ". ");
        }
    }

    /*
    public void generateFruit(Inventory inventory, int itemsRecieved){
        // gets the previous amount of wood in inventory from the Hashmap in inventory
        int woodInInventory = inventory.getItems().getOrDefault("wood",0);
        // adds 1
        inventory.getItems().put("wood", woodInInventory + itemsRecieved);
    }

    public void tryGenerateWood(Habitat habitat, Inventory inventory, Zoo zoo){
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

        // if the randomized int is equal to 1, the Zoo will receive a wood that will be added to inventory
        if (randomInt == 1){

            randomInt = myrandom.nextInt(3)+1;
            int itemsRecieved = randomInt*3;

            System.out.println(zoo.getName() +" recieved wood from the "+ habitat.getHabitatName()+ ".");
            System.out.println(itemsRecieved + " Wood added to inventory.");
            generateFruit(inventory, itemsRecieved);
        }
    }

     */

}
