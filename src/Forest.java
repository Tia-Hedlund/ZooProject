import java.util.Random;

public class Forest extends WoodlandHabitat{

    // constructor
    public Forest(double price, String habitatName, int habitatLevel, int creatureLevelLimit, Biome habitatBiome, int totalLevelInHabitat) {
        super(price, habitatName, habitatLevel, creatureLevelLimit, habitatBiome, totalLevelInHabitat);
    }

    // overrides the tryGenerateItem (explained in coralReef method)
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

}
