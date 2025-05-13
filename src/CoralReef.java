import java.util.Random;

public class CoralReef extends OceanHabitat{
    public CoralReef(double price, String habitatName, int habitatLevel, int creatureLimit, Biome habitatBiome) {
        super(price, habitatName, habitatLevel, creatureLimit, habitatBiome);
    }

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
}

