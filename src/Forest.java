import java.util.Random;
import java.util.Scanner;

public class Forest extends WoodlandHabitat{

    Scanner scanner = new Scanner(System.in);

    public Forest(double price, String habitatName, int habitatLevel, int creatureLevelLimit, Biome habitatBiome, int totalLevelInHabitat) {
        super(price, habitatName, habitatLevel, creatureLevelLimit, habitatBiome, totalLevelInHabitat);
    }

    //@Override
    public void tryGenerateItem(Scanner scanner, Inventory inventory, Zoo zoo){
        Random myrandom = new Random();

        if (inventory.getStorageUsed()>=zoo.getMaxStorage()){
            System.out.println("Storage is full. Cannot collect wood from " + getHabitatName() + ". ");
            return;
        }

        double baseDropRate = 0.3 + (getHabitatLevel() *0.05);
        double randomDouble = myrandom.nextDouble();

        if (randomDouble < baseDropRate){

        }
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

    public void generateFruit(Inventory inventory, int itemsRecieved){
        // gets the previous amount of wood in inventory from the Hashmap in inventory
        int woodInInventory = inventory.getItems().getOrDefault("wood",0);
        // adds 1
        inventory.getItems().put("wood", woodInInventory + itemsRecieved);
    }
}
