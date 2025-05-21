import java.util.ArrayList;
import java.util.Scanner;

public class Shop {
    // creates attricutes and scanner
    Scanner scanner = new Scanner(System.in);
    private ArrayList<Habitat> habitatsForSale;
    private ArrayList<Creature> creaturesForSale;

    // constructor, creates arraylists
    public Shop(){
        habitatsForSale = new ArrayList<>();
        creaturesForSale = new ArrayList<>();
    }

    //getters and setters
    public ArrayList<Habitat> getHabitatsForSale() {
        return habitatsForSale;
    }

    public ArrayList<Creature> getCreaturesForSale() {
        return creaturesForSale;
    }

    // ***** Methods Add for Sale *****

    // methods adds and removes creatures and habitats from the arraylists above
    public void addHabitatForSale(Habitat habitat){
        habitatsForSale.add(habitat);
    }

    public void removeHabitatForSale(Habitat habitat){
        habitatsForSale.remove(habitat);
    }

    public void addCreatureForSale(Creature creature){
        creaturesForSale.add(creature);
    }

    public void removeCreatureForSale(Creature creature){
        creaturesForSale.remove(creature);
    }

    // ***** Methods Sell *****

    // method to check if there are any items inventory to be sold, returns boolean true if yes false if no
    public boolean canSellItems(Inventory inventory, String itemName, int quantity){
        if (inventory.getItems().getOrDefault(itemName, 0)>=quantity){
            return true;
        }
        else{
            return false;
        }
    }

    // method for selling items
    public void sellItems(Inventory inventory, Zoo zoo, String itemName, int quantity, Item item) {
        // if the method cansellitems returned true then...
        if (canSellItems(inventory, itemName, quantity)){
            // decreases with the quantity that the user chose
            inventory.decreaseItem(itemName, quantity);
            // sets the zoo money to the increased amount after selling
            zoo.setMoney(zoo.getMoney()+(quantity*(item.getPrice()/2)));

            System.out.println(quantity + " " + itemName + " sold for "+ ((item.getPrice()/2)*quantity)+" coins.");
            System.out.println("Total Coins: "+zoo.getMoney());
        }
        else if (inventory.getItems().getOrDefault(itemName, 0)== 0){
            System.out.println("No "+ itemName + " in inventory.");
        }
        else{
            System.out.println("Not enough "+ itemName + " in inventory to sell "+ quantity + " "+itemName+".");
            System.out.println("In inventory: "+inventory.getItems().getOrDefault(itemName, 0)+ "/"+ quantity);
        }

    }

    // ***** Methods Buy *****

    // returns boolean, checks if canBuyItems
    public boolean canBuyItems(Zoo zoo, Buyable item, Inventory inventory, int quantity){
        if (zoo.getMoney() >= item.getPrice()*quantity && ((zoo.getMaxStorage()-inventory.getStorageUsed())>=quantity)){
            return true;
        }
        else{
            return false;
        }
    }

    public void buyItems(Zoo zoo, Buyable item, Inventory inventory, int quantity){
        if (canBuyItems(zoo, item, inventory, quantity)){
            // Saves the item (fruit wood or fish) that the player chose to buy in a String "boughtItem"
            String boughtItem = item.getClass().getSimpleName().toLowerCase();

            // Add the quantity bought to the total of that item
            // in the inventory Hashmap "items", the previous amount of that item + quantity, is added.
            inventory.getItems().put(boughtItem, inventory.getItems().getOrDefault(boughtItem, 0) + quantity);

            // Reduce the total price from the Zoo total money.
            // the Zoo's money is set to the difference between the Zoos previous money and the cost of the users chosen quantity.
            zoo.setMoney(zoo.getMoney()-item.getPrice()*quantity);

            // Update storageUsed with the increaseStorageUsed method from inventory.
            inventory.increaseStorageUsed(quantity);
            System.out.println(quantity + " " + boughtItem + " purchased for "+ item.getPrice()*quantity+" coins.");
        }
        else if (zoo.getMoney() < item.getPrice()*quantity) {
            System.out.println("Could not purchase. Not enough Coins.");
            System.out.println("["+zoo.getMoney()+"/"+item.getPrice()*quantity+"]");
        }
        else if ((zoo.getMaxStorage()-inventory.getStorageUsed())<quantity){
            System.out.println("Could not purchase " +quantity+ " "+ item.getClass().getSimpleName().toLowerCase() + ". Not enough Storage.");
            System.out.println("Storage used: ["+inventory.getStorageUsed()+"/"+zoo.getMaxStorage()+"]");

        }
        else{
            System.out.println("Could not purchase.");
        }
    }


    public boolean canBuyHabitat(Zoo zoo, Habitat habitat){

        // Make it so that a certain zoo level is required to unluck certain habitats and creatures.
        // Looks through the arraylist habitats
        for (Habitat h : zoo.getHabitats()){
            // if any of the habitats found in habitats are of the exact same class as the one the user is trying to buy
            if (h.getClass().equals(habitat.getClass())){
                // it will return false, meaning that the user cannot buy the habitat
                return false;
            }
        }

        // if no habitats of the exact same class were found:
        if (zoo.getMoney() >= habitat.getPrice()){
            // the player has enough money
            return true;
        }
        else{
            // the player doesn't have enough money
            return false;
        }
    }

    public void buyHabitat(Zoo zoo, Habitat habitat){
        if(canBuyHabitat(zoo, habitat)){
            // Reduce the total price of the habitat from the Zoo's total money.
            zoo.setMoney(zoo.getMoney()-habitat.getPrice());
            // the bought habitat is added to the Arraylist habitats in zoo.
            zoo.addHabitatToZoo(habitat);

            // removes the bought habitat from the for sale list
            removeHabitatForSale(habitat);
            System.out.println(habitat.getHabitatName()+ " purchased for "+ habitat.getPrice()+".");
        }
        else if(zoo.getMoney() < habitat.getPrice()){
            System.out.println("Could not purchase habitat. Not enough coins.");
        }
        else{
            System.out.println("Could not purchase habitat. "+habitat.getHabitatName()+ " already owned.");
        }
    }

    // method checks if canBuyCreature, if yes return true if no return false
    public boolean canBuyCreature(Zoo zoo, Creature creature, Habitat habitat){
        if (zoo.getMoney() >= creature.getPrice() && habitat.canAddCreatureToHabitat(creature, zoo)){
            return true;
        }
        else{
            return false;
        }
    }

    public ArrayList<Habitat> getMatchingHabitats(Creature creature, Zoo zoo){
        ArrayList<Habitat> biomeMatchingHabitats = new ArrayList();
        for (Habitat h : zoo.getHabitats()){
            // If the biome of the habitat h from all the owned habitats, is compatible with the creatures biome...
            if (h.getHabitatBiome().BiomesAreCompatible(creature)){
                biomeMatchingHabitats.add(h);
            }
        }
        return biomeMatchingHabitats;

    }

    public Habitat chooseCreaturesHabitat(Scanner myScanner, Creature creature, Zoo zoo){
        ArrayList<Habitat> biomeMatchingHabitats = getMatchingHabitats(creature, zoo);

        if (biomeMatchingHabitats.isEmpty()){
            System.out.println("No habitats compatible with "+ creature.getCreatureName()+ " owned.");
            return null;
        }

        System.out.println("To purchase "+ creature.getCreatureName() +" please select a habitat.");
        System.out.println("Compatible Habitats:");
        System.out.printf("%-17s %-15s %-19s %s\n", "Habitat:", "   Level:", "   Status:", "   Creatures:");

        while(true) {
            // how this works is explained is ZooGame class
            for (int i = 0; i < biomeMatchingHabitats.size(); i++) {
                Habitat h = biomeMatchingHabitats.get(i);

                String habitatName = h.getHabitatName();
                int level = h.getHabitatLevel();
                int totalUsedLevel = h.getTotalLevelInHabitat();
                ArrayList<Creature> creatures = h.getCreatures();

                StringBuilder creatureList = new StringBuilder("[");
                for (int j = 0; j < creatures.size(); j++) {
                    Creature c = creatures.get(j);
                    creatureList.append(c.getCreatureName()).append(" - lvl ").append(c.getCreatureLevel());
                    if (j != creatures.size() - 1) {
                        creatureList.append(", ");
                    }
                }
                creatureList.append("]");
                String status = "";
                if (totalUsedLevel == level) {
                    status = "\033[31mFull\033[0m          ";
                } else {
                    status = "Available          ";
                }

                System.out.print(i + 1 + ". ");
                System.out.printf("%-17s %-15s %-15s %s\n", habitatName, level, status, creatureList);
            }

            System.out.println((biomeMatchingHabitats.size() + 1) + ". Cancel");
            System.out.println("Choose an option: ");


            String answer = myScanner.nextLine();

            int intChoice;
            // also explained in zooGameclass
            try {
                intChoice = Integer.parseInt(answer);

                if (intChoice == (biomeMatchingHabitats.size() + 1)) {
                    return null;
                } else if (intChoice >= 1 && intChoice <= biomeMatchingHabitats.size()) {
                    Habitat chosenHabitat = biomeMatchingHabitats.get(intChoice - 1);
                    if (chosenHabitat.getTotalLevelInHabitat() >= chosenHabitat.getHabitatLevel()){
                        System.out.println("This habitat is \033[31mfull\033[0m. Cannot add "+ creature.getCreatureName() +" to habitat.");
                        System.out.println("Choose a habitat to add "+creature.getCreatureName() + " too or choose to cancel.");
                        continue;
                    }
                    return chosenHabitat;
                } else {
                    System.out.println("Please enter a number between (1-" + (biomeMatchingHabitats.size() + 1) + ")");

                }
            } catch (Exception e) {
                System.out.println("Please enter a valid number.");

            }
        }
    }

    public void buyCreature(Zoo zoo, Creature creature){
        Habitat chosenHabitat = chooseCreaturesHabitat(scanner, creature, zoo);

        if (chosenHabitat == null){
            System.out.println("Purchase cancelled.");
            return;
        }

        if(canBuyCreature(zoo, creature, chosenHabitat)){
            // Reduce the total price of the creature from the Zoo's total money.
            zoo.setMoney(zoo.getMoney()-creature.getPrice());
            // the bought creature is added to the Arraylist creatures in habitats and creatures in zoo.
            chosenHabitat.addCreatureToHabitatAndZoo(creature, zoo);
            // remove the bought creature from the for sale Arraylist in shop
            removeCreatureForSale(creature);

            System.out.println(creature.getCreatureName() +" purchased for "+creature.getPrice()+" and added to the "+ chosenHabitat.getHabitatName()+ ".");
        }
        else{
            System.out.println("Could not proceed with purchase.");
        }
    }

    // checks if can buy gaurd, returns true or false depending on the if statment
    public boolean canBuyGuard(Zoo zoo, Guard guard){
        if (zoo.getMoney() >= (guard.getTotalPrice(zoo))){
            return true;
        }
        else{
            System.out.println("Could not purchase a Guard. Not enough coins.");
            return false;
        }
    }

    public void buyGuard(Zoo zoo, Guard guard){
        if (canBuyGuard(zoo, guard)){
            // Reduce the total price of the guard from the Zoo's total money.
            zoo.setMoney(zoo.getMoney()-guard.getTotalPrice(zoo));
            System.out.println("1 Guard purchased for "+ guard.getTotalPrice(zoo) +" coins.");
            zoo.addGuardToZoo();
        }
    }

    // ***** Methods Buy Upgrades *****

    public void buyUpgradeZoo(Scanner scanner, Zoo zoo, Inventory inventory){

        int woodNeeded = zoo.getIntWoodNeeded();
        double upgradeCost = zoo.getUpgradeCost();

        System.out.printf("%-19s %s\n", "Upgrade method:", "   Amount Required:");
        System.out.print("1. ");
        System.out.printf("%-19s %s\n", "Wood", +zoo.getIntWoodNeeded());
        System.out.print("2. ");
        System.out.printf("%-19s %s\n", "Coins", +zoo.getUpgradeCost());
        System.out.println("3. Back");
        System.out.print("Choose an option (1/2/3): ");

        String answer = scanner.nextLine();

        // calls different methods depending on the users input
        switch (answer){
            case "1":
                zoo.upgradeZooWood(inventory);
                break;
            case "2":
                zoo.upgradeZooMoney();
                break;
            case "3":
                return;
            default:
                System.out.println("Please enter a valid number.");
        }
    }

    public void buyHabitatUpgrade(Habitat habitat, Zoo zoo, Inventory inventory){
        System.out.printf("%-19s %s\n", "Upgrade method:", "   Amount Required:");
        System.out.print("1. ");
        System.out.printf("%-19s %s\n", "Wood", "  "+habitat.getIntWoodNeeded());
        System.out.print("2. ");
        System.out.printf("%-19s %s\n", "Coins", "  "+habitat.getUpgradeCost());
        System.out.println("3. Back");
        System.out.print("Choose an option (1/2/3): ");

        String answer = scanner.nextLine();

        switch (answer){
            case "1":
                // creates wood so that the woods method useTopUpgrade can be called
                Wood wood = new Wood(0);
                wood.useToUpgrade(habitat, inventory);
                break;
            case "2":
                // if user chose money then habitat gets upgraded with money if the method canUpgrade returns boolean true
                if (habitat.canUpgrade("money", zoo)){
                    habitat.upgradeReduceMoney(zoo);
                    habitat.upgrade();
                }
                else{
                    System.out.println("Could not upgrade "+habitat.getHabitatName()+ ". Not enough coins.");
                }
                break;
            case "3":
                return;
            default:
                System.out.println("Please enter a valid number.");
        }
    }

    public boolean canUpgradeCreature(Creature creature){
        Habitat creaturesHabitat = creature.getHabitat();
        System.out.println("Total Creature level in "+creaturesHabitat.getHabitatName()+": "+creaturesHabitat.getTotalLevelInHabitat());
        System.out.println("Creature level limit in "+creaturesHabitat.getHabitatName()+": "+ creaturesHabitat.getCreatureLevelLimit());
        if (creaturesHabitat.getTotalLevelInHabitat()>=creaturesHabitat.getCreatureLevelLimit()){
            return false;
        }
        else{
            return true;
        }
    }

    public void buyCreatureUpgrade(Creature creature, Zoo zoo, Inventory inventory) {
        // prints menu
        System.out.printf("%-19s %s\n", "Upgrade method:", "   Amount Required:");
        System.out.print("1. ");
        System.out.printf("%-19s %s\n", "Fruit", "  " + creature.getFoodRequired());
        System.out.print("2. ");
        System.out.printf("%-19s %s\n", "Fish", "  " + creature.getFoodRequired());
        System.out.print("3. ");
        System.out.printf("%-19s %s\n", "Coins", "  " + creature.getUpgradeCost());
        System.out.println("4. Back");
        System.out.print("Choose an option (1/2/3/4): ");

        String answer = scanner.nextLine();

        switch (answer) {
            case "1":
                // creates fruit so the fruit use to upgrade method can be called
                Fruit fruit = new Fruit(0);
                fruit.useToUpgrade(creature, inventory);
                break;
            case "2":
                // same as with fruit
                Fish fish = new Fish(0);
                fish.useToUpgrade(creature, inventory);
                break;
            case "3":
                // if player chose to upgrade with money, creature gets upgradded if the canUpgrademoney method returns true
                if (creature.canUpgradeMoney(zoo)){
                    creature.reduceUpgradeMoney(zoo);
                    // the habitat that is used as a parameter in the creature upgrade is saved here in habiat
                    Habitat habitat = creature.getHabitat();
                    creature.upgrade(habitat);
                }
                else{
                    System.out.println("Could not upgrade "+ creature.getCreatureName()+". Not enough coins.");
                }
                break;
            case "4":
                return;
            default:
                System.out.println("Please enter a valid number.");
        }
    }
}
