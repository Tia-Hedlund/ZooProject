import java.util.Random;

public class Forest extends WoodlandHabitat{
    public Forest(double price, String habitatName, int habitatLevel, int creatureLimit, Biome habitatBiome) {
        super(price, habitatName, habitatLevel, creatureLimit, habitatBiome);
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
