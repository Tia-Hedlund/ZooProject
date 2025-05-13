import java.util.Random;

public class Savannah extends DrylandHabitat{

    public Savannah(double price, String habitatName, int habitatLevel, int creatureLevelLimit, Biome habitatBiome, int totalLevelInHabitat) {
        super(price, habitatName, habitatLevel, creatureLevelLimit, habitatBiome, totalLevelInHabitat);
    }

    public void tryGenerateFruit(Habitat habitat, Inventory inventory, Zoo zoo){
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

        // if the randomized int is equal to 1, the Zoo will receive a fruit that will be added to inventory
        if (randomInt == 1){


            randomInt = myrandom.nextInt(1)+1;
            int itemsRecieved = randomInt*1;

            System.out.println(zoo.getName() +" recieved fruit from the "+ habitat.getHabitatName()+ ".");
            System.out.println(itemsRecieved+ " Fruit added to inventory.");
            generateFruit(inventory, itemsRecieved);
        }
    }

    public void generateFruit(Inventory inventory, int itemsRecieved){
        // gets the previous amount of fruit in inventory from the Hashmap in inventory
        int fruitInInventory = inventory.getItems().getOrDefault("fruit",0);
        // adds 1
        inventory.getItems().put("fruit", fruitInInventory + itemsRecieved);
    }
}
