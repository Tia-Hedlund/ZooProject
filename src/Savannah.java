import java.util.Random;

public class Savannah extends DrylandHabitat{

    public Savannah(double price, String habitatName, int habitatLevel, int creatureLevelLimit, Biome habitatBiome, int totalLevelInHabitat) {
        super(price, habitatName, habitatLevel, creatureLevelLimit, habitatBiome, totalLevelInHabitat);
    }

    /*
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

     */

    @Override
    public void tryGenerateItem(Inventory inventory, Zoo zoo){
        Random myrandom = new Random();

        if (inventory.getStorageUsed()>=zoo.getMaxStorage()){
            System.out.println("Storage is full. Could not collect fruit from " + getHabitatName() + ". ");
            return;
        }

        double baseDropRate = 0.2 + (getHabitatLevel() *0.05);
        double randomDouble = myrandom.nextDouble();

        System.out.println("BaseDrop: "+ baseDropRate);
        System.out.println("Random: "+ randomDouble);

        if (randomDouble < baseDropRate){
            // create an int to set a maximum amount of wood that can be generated.
            int woodGenCap = getHabitatLevel();
            // Generates a random number between 1 and the maximum amount of wood that can be generated
            int quantity = 1+myrandom.nextInt(woodGenCap);

            int spaceInStorage = zoo.getMaxStorage()-inventory.getStorageUsed();
            // if the random quantity is bigger than the available space in storage, the smaller of the values will be chosen.
            // cant generate more than the available space.
            int allowedQuantity = Math.min(quantity, spaceInStorage);

            inventory.increaseItem("fruit", allowedQuantity);
            System.out.println(allowedQuantity + " fruit collected from " +getHabitatName()+ ". ");
        }
    }

}
