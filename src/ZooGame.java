import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Thread.*;

public class ZooGame {
    public void StartZoo() {
        int day = 1;
        Scanner myScanner = new Scanner(System.in);

        System.out.println("\033[31mThis is not a normal Zoo.");
        System.out.println("This is...\033[0m");

        System.out.println("[Please name your zoo]");


        String zooName = myScanner.nextLine();
        System.out.println();

        // Starting Zoo and creating all the objects:
        Zoo zoo = new Zoo(zooName, 100, 50, 0, 1);
        Shop shop = new Shop();
        Fish fish = new Fish(100.0);
        Fruit fruit = new Fruit(100.0);
        Wood wood = new Wood(25.0);
        Inventory inventory = new Inventory(0);
        Guard guard = new Guard(50.0);

        Biome woodlandBiome = new WoodlandBiome();
        Biome drylandBiome = new DrylandBiome();
        Biome oceanBiome = new OceanBiome();

        Habitat meadow = new Meadow(100.0, "Meadow", 1, 1, woodlandBiome, 0);
        Habitat forest = new Forest(1000.0, "Forest", 1, 1, woodlandBiome, 0);
        Habitat desert = new Desert(3000.0, "Desert", 1, 1, drylandBiome, 0);
        Habitat savannah = new Savannah(10000.0, "Savannah", 1, 1, drylandBiome, 0);
        Habitat coralReef = new CoralReef(15000.0, "Coral Reef", 1, 1, oceanBiome, 0);
        Habitat glacier = new Glacier(6000.0, "Glacier", 1, 1, oceanBiome, 0);

        // adding them to the Arraylist HabitatForSale in shop
        shop.addHabitatForSale(forest);
        shop.addHabitatForSale(desert);
        shop.addHabitatForSale(savannah);
        shop.addHabitatForSale(coralReef);
        shop.addHabitatForSale(glacier);

        Creature twiglet = new Creature(100.0, woodlandBiome, 1, 0, "Twiglet", 1, 5, 1);
        Creature snufflebud = new Creature(200.0, woodlandBiome, 1, 0, "Snufflebud", 1, 10, 1);
        Creature gloamlet = new Creature(500.0, woodlandBiome, 2, 0, "Gloamlet", 1, 15, 1);
        Creature grimthicket = new Creature(650.0, woodlandBiome, 2, 0, "Grimthicket", 1, 16, 1);
        Creature sawtooth = new Creature(1000.0, woodlandBiome, 3, 0, "Sawtooth", 1, 18, 1);
        Creature dreadmoss = new Creature(1200.0, woodlandBiome, 3, 0, "Dreadmoss", 1, 20, 1);

        Creature reek = new Creature(200.0, drylandBiome, 1, 0, "Reek", 1, 5, 1);
        Creature scavenger = new Creature(300.0, drylandBiome, 1, 0, "Scavenger", 1, 7, 1);
        Creature scorchtail = new Creature(1000.0, drylandBiome, 2, 0, "Scorchtail", 1, 20, 1);
        Creature trub = new Creature(1050.0, drylandBiome, 2, 0, "Trub", 1, 21, 1);
        Creature kraalhorn = new Creature(3100.0, drylandBiome, 3, 0, "Kraalhorn", 1, 38, 1);
        Creature heatwyrm = new Creature(5000.0, drylandBiome, 4, 0, "Heat Wyrm", 1, 56, 1);

        Creature glimmer = new Creature(400.0, oceanBiome, 1, 0, "Glimmer", 1, 15, 1);
        Creature shellveil = new Creature(520.0, oceanBiome, 1, 0, "Shellveil", 1, 17, 1);
        Creature lurefin = new Creature(1600.0, oceanBiome, 2, 0, "Lurefin", 1, 31, 1);
        Creature serelune = new Creature(2030.0, oceanBiome, 3, 0, "Serelune", 1, 46, 1);
        Creature vessiryn = new Creature(3200.0, oceanBiome, 4, 0, "Vessiryn", 1, 58, 1);
        Creature boreala = new Creature(12000.0, oceanBiome, 5, 0, "Boreala", 1, 102, 1);


        // Adding all the creatures
        shop.addCreatureForSale(snufflebud);
        shop.addCreatureForSale(gloamlet);
        shop.addCreatureForSale(grimthicket);
        shop.addCreatureForSale(sawtooth);
        shop.addCreatureForSale(dreadmoss);

        shop.addCreatureForSale(reek);
        shop.addCreatureForSale(scavenger);
        shop.addCreatureForSale(scorchtail);
        shop.addCreatureForSale(trub);
        shop.addCreatureForSale(kraalhorn);
        shop.addCreatureForSale(heatwyrm);

        shop.addCreatureForSale(glimmer);
        shop.addCreatureForSale(shellveil);
        shop.addCreatureForSale(lurefin);
        shop.addCreatureForSale(serelune);
        shop.addCreatureForSale(vessiryn);
        shop.addCreatureForSale(boreala);


        // Adding the beginner/base habitat and creautre to zoo
        zoo.addHabitatToZoo(meadow);
        meadow.addCreatureToHabitatAndZoo(twiglet, zoo);

        // printing the zoo and habitat stats by calling methods from zoo
        System.out.println();
        zoo.printZooStats();
        zoo.printHabitatStats();

        // printing some information for first time players
        System.out.println("The creatures you own (Current creatures owned: " + twiglet.getCreatureName() + ") will generate coins which you can collect at the end of each day.");
        System.out.println("The coins will be added to your Zoo's total coins (Current coins: " + zoo.getMoney() + ")");
        System.out.println("Moreover, the coins can be used to buy 'Items', 'Habitats', 'Creatures' and 'Guards' in the 'Shop'.");
        System.out.println("Or to upgrade the level of your Zoo, owned habitats or creatures. ");
        System.out.println("Some habitats and some creatures may differ from others, but that's a problem for future you.");
        System.out.println("Good Luck!");

        //creating a boolean called playing and setting it to true
        boolean playing = true;
        // the game loop, which is active as long as playing is true
        while (playing) {
            System.out.println();
            // printing the day
            System.out.println("✦ʚ♡ɞ✦ Day " + day + " ✦ʚ♡ɞ✦");

            // create a boolean, set to true
            boolean dayActive = true;
            // a while loop that goes as long as dayActive is true
            while (dayActive) {
                // if showMainMenu returns false the boolean "dayActive" will become false which ends the loop
                dayActive = showMainMenu(myScanner, zoo, day, shop, inventory, wood, fish, fruit, guard);

            }
            // calls the nighttime method from zoo and endday and increases the int day
            zoo.nightTime(shop, inventory, zoo);
            endDay(zoo, day);
            day++;

        }
    }

    // method showMainmenu that returns a boolean which makes dayActive true or false
    private boolean showMainMenu(Scanner scanner, Zoo zoo, int day, Shop shop, Inventory inventory, Wood wood, Fish fish, Fruit fruit, Guard guard) {
        System.out.println();
        // prints the menu
        System.out.println("Zoo Actions: ");
        System.out.println("1. Visit Shop");
        System.out.println("2. View Creatures and Habitats");
        System.out.println("3. View Zoo stats");
        System.out.println("4. View Inventory");
        System.out.println("5. Feed Creature");
        System.out.println("6. End Day");
        System.out.println("7. Quit Game");
        System.out.print("Choose an action: ");


        // scanner takes in user input as String in stringAnswer
        String stringAnswer = scanner.nextLine();
        // the user input is made lowercase
        String answer = stringAnswer.toLowerCase();
        System.out.println();
        // switch case based on the answer
        switch (answer) {
            case "1":
                // calls visitshop menu
                visitShopMenu(scanner, zoo, day, shop, inventory, wood, fish, fruit, guard);
                break;
            case "2":
                // calls printHabitatstats from zoo class
                zoo.printHabitatStats();
                break;
            case "3":
                //calls printzoostats from zoo class
                zoo.printZooStats();
                break;
            case "4":
                // calls print inventory from intentory, method uses zoo as parameter
                inventory.printInventory(zoo);
                break;
            case"5":
                // calls feedcreaturemenu with its parameters
                feedCreatureMenu(zoo, scanner, inventory);
                break;
            case "6":
                // if answer is "6" then method returns false, which makes dayActive false
                return false;
            case "7":
                System.out.println("Quitting game.");
                System.exit(0);
            default:
                System.out.println("Please enter 1/2/3/4/5/6/7.");
        }
        // unless player chose 6 dayActive is still active by returning true
        return true;
    }

    // method that endsDay
    private void endDay(Zoo zoo, int day) {
        System.out.println("Ending day " + day + ".");
        // for all the creatures in the zoo that the player has it will loop through them in the arraylist
        for (Creature creature : zoo.getCreatures()) {
            // and call the claim profit method for each one
            zoo.claimProfit(creature);
        }
        // print total coins
        System.out.println("Total Coins: " + zoo.getMoney());
    }


    private void feedCreatureMenu(Zoo zoo, Scanner scanner, Inventory inventory){
        System.out.println("Owned Creatures:");
        System.out.printf("%-17s %-19s %s\n", "Creature:", "   Pacified Level:", "   Gold Profit Boost:");

        // for loop that goes on for the same amount of creatures that are in the zoo
        for (int i = 0; i < zoo.getCreatures().size(); i++) {

            // for all creatures in the arraylist creatures in zoo they will get them one by one (c) as it loops through the for loop
            Creature c = zoo.getCreatures().get(i);

            // creates a string creatureName that gets its value from the specific creatures gotten from the arraylists name
            String creatureName = c.getCreatureName();
            // same here
            int pacifyLevel = c.getPacifyLevel();
            // same here
            double goldProfitBoost = c.getCreatureGoldBonus();

            System.out.print(i + 1 + ". ");
            // print it out
            System.out.printf("%-17s %-19s %s\n", creatureName, pacifyLevel, goldProfitBoost);
        }

        // making the number for the last option back
        System.out.println((zoo.getCreatures().size()+1)+". Back");
        System.out.print("Choose an option (1-"+(zoo.getCreatures().size()+1)+"): ");

        // takes in input as answer in a String
        String answer = scanner.nextLine();
        System.out.println();

        int intChoice;
        // try catch to see if user chose correct input and what choice they made
        try {
            // tries to parse the choice and make it an int if it can
            intChoice = Integer.parseInt(answer);

            // if the int is the same as the number before "back" then return;
            if (intChoice == (zoo.getCreatures().size() +1)){
                return;
            }
            // if the choice is any of the creatures then...
            else if (intChoice >= 1 && intChoice <= zoo.getCreatures().size()){
                // the creature in the arraylist that the player chose becomes the chosenCreature type Creature
                Creature chosenCreature = zoo.getCreatures().get(intChoice-1);

                // calls the method choose food menu which uses chosenCreature as a parameter
                chooseFoodMenu(scanner, inventory, chosenCreature);
            }
            else{
                System.out.println("Please enter a number between (1-"+(zoo.getCreatures().size() +1) +")");
            }
        } catch (Exception e){
            System.out.println("Please enter a number between (1-"+(zoo.getCreatures().size() +1) +")");
        }
    }

    private void chooseFoodMenu(Scanner scanner, Inventory inventory, Creature chosenCreature) {
        System.out.println();
        System.out.printf("%-17s %-17s %s\n", "Food:", "   In inventory:", "   Amount Required:");
        System.out.print("1. ");
        System.out.printf("%-17s %-17s %s\n", "Fish", inventory.getItems().getOrDefault("fish", 0), chosenCreature.fishNeededToFeed());
        System.out.print("2. ");
        System.out.printf("%-17s %-17s %s\n", "Fruit", inventory.getItems().getOrDefault("fruit", 0), chosenCreature.fruitNeededToFeed());
        System.out.println("3. Back");
        System.out.print("Choose an option: ");

        String stringAnswer = scanner.nextLine();
        String answer = stringAnswer.toLowerCase();
        System.out.println();
        // switch case depending on answer
        switch (answer) {
            case "1":
                // creates a fish, since inventorys hashmap is not dependent on the actual item when it counts, the price can be 0
                Fish fish = new Fish(0);
                // calls the method useToFeed for a fish using the chosenCreature from the method before as a parameter
                fish.useToFeed(chosenCreature, inventory);
                break;
            case "2":
                Fruit fruit = new Fruit(0);
                fruit.useToFeed(chosenCreature, inventory);
                break;
            case "3":
                return;
            default:
        }
    }

    private void sellItemMenu(Scanner scanner, Shop shop, Inventory inventory, Zoo zoo, Wood wood, Fish fish, Fruit fruit){
        inventory.printInventory(zoo);
        System.out.println("Sell Items:");
        System.out.printf("%-12s %s\n", "Item:", "   Sell Price:");
        System.out.print("1. ");
        System.out.printf("%-12s %s\n", "Wood", (wood.getPrice()/2));
        System.out.print("2. ");
        System.out.printf("%-12s %s\n", "Fish", (fish.getPrice()/2));
        System.out.print("3. ");
        System.out.printf("%-12s %s\n", "Fruit", (fruit.getPrice()/2));
        System.out.println("4. Back");
        System.out.print("Choose an option: (1/2/3/4): ");

        String answer = scanner.nextLine().toLowerCase();
        int quantity = 0;
        String item = "";

        // switch case dependent on players input answer as String
        switch (answer) {
            case "1":
                // if player chose 1 - wood, then it will set a String called items to the wood-classes name in lowercase so that it works in inventory
                item = wood.getClass().getSimpleName().toLowerCase();
                // quantity an int is set to what the method enterquantity returns
                quantity = enterQuantity(scanner, item, "sell");
                // calls the shop method sellItems which need item and quantity as parameters
                shop.sellItems(inventory, zoo, item, quantity, wood);
                break;
            case "2":
                // same as case 1
                item = fish.getClass().getSimpleName().toLowerCase();
                // same as case 1
                quantity = enterQuantity(scanner, item, "sell");
                // same as case 1
                shop.sellItems(inventory, zoo, item, quantity, fish);
                break;
            case "3":
                item = fruit.getClass().getSimpleName().toLowerCase();
                quantity = enterQuantity(scanner, item, "sell");
                shop.sellItems(inventory, zoo, item, quantity, fruit);
                break;
            case "4":
                return;
            default:
                System.out.println();
        }
    }

    private void visitShopMenu(Scanner scanner, Zoo zoo, int day, Shop shop, Inventory inventory, Wood wood, Fish fish, Fruit fruit, Guard guard) {
        System.out.println();
        System.out.println("Shop:");
        System.out.println("1. Items");
        System.out.println("2. Habitats");
        System.out.println("3. Creatures");
        System.out.println("4. Guards");
        System.out.println("5. Upgrades");
        System.out.println("6. Sell Items");
        System.out.println("7. Back");
        System.out.print("Choose an option: ");

        String answer = scanner.nextLine().toLowerCase();
        System.out.println();

        switch (answer) {
            case "1":
                buyItemsMenu(scanner, shop, zoo, inventory, wood, fish, fruit, day);
                break;
            case "2":
                buyHabitatsMenu(scanner, shop, zoo);
                break;
            case "3":
                buyCreatureMenu(scanner, shop, zoo);
                break;
            case "4":
                buyGuardMenu(scanner, guard, shop, zoo);
                break;
            case "5":
                buyUpgradesMenu(scanner, shop, zoo, inventory);
                break;
            case "6":
                sellItemMenu(scanner, shop, inventory, zoo, wood, fish, fruit);
                break;
            case "7":
                return;
            default:
                System.out.println("Please enter (1/2/3/4/5/6/7)");

        }
    }

    private void buyItemsMenu(Scanner scanner, Shop shop, Zoo zoo, Inventory inventory, Wood wood, Fish fish, Fruit fruit, int day) {
        System.out.println();
        System.out.println("Shop - Items:");
        System.out.println();
        System.out.printf("%-12s %s\n", "Item:", "   Price:");
        System.out.print("1. ");
        System.out.printf("%-12s %s\n", "Wood", wood.getPrice());
        System.out.print("2. ");
        System.out.printf("%-12s %s\n", "Fish", fish.getPrice());
        System.out.print("3. ");
        System.out.printf("%-12s %s\n", "Fruit", fruit.getPrice());
        System.out.println("4. Back");
        System.out.print("Choose an option: (1/2/3/4): ");

        String answer = scanner.nextLine().toLowerCase();
        int quantity = 0;
        String item = "";

        // a switch case dependent on player input String answer
        switch (answer) {
            case "1":
                // a string called item is again set to the simple word of the class wood
                item = wood.getClass().getSimpleName().toLowerCase();
                // quantity gets the value that enterQuantity method returns
                quantity = enterQuantity(scanner, item, "purchase");
                // calls shop buyItems method
                shop.buyItems(zoo, wood, inventory, quantity);
                break;
            case "2":
                item = fish.getClass().getSimpleName().toLowerCase();
                quantity = enterQuantity(scanner, item, "purchase");
                shop.buyItems(zoo, fish, inventory, quantity);
                break;
            case "3":
                item = fruit.getClass().getSimpleName().toLowerCase();
                quantity = enterQuantity(scanner, item, "purchase");
                shop.buyItems(zoo, fruit, inventory, quantity);
                break;
            case "4":
                return;
            default:
                System.out.println();
        }
    }

    // uses the string item and a string action to print correctly inside the method
    private int enterQuantity(Scanner scanner, String item, String action) {
        // sets quantity to 0
        int quantity = 0;
        // creatues boolean with default value false
        boolean intInput = false;


        // while intInput is false
        while (!intInput) {;
            System.out.print("Enter a number of " + item + " to "+ action +": ");
            // if the players input is an int then intInput becomes true
            try {
                quantity = scanner.nextInt();
                scanner.nextLine();
                intInput = true;
                // calls exception to catch if player did'nt enter an int
            } catch (Exception e) {
                System.out.println("Enter a number.");
                // empties the scanner, clears the input so that its not nonInt within the scanners input
                scanner.nextLine();
            }
        }
        // returns the quantity when player gave correct input
        return quantity;
    }

    private void buyHabitatsMenu(Scanner scanner, Shop shop, Zoo zoo) {
        System.out.println();

        // if the arraylist HabitatsFor sale in shop is empty then what the if statement contains will happen
        if (shop.getHabitatsForSale().isEmpty()) {
            // prints to user that they cant buy any habitats
            System.out.println("No habitats available for purchase.");
            // ends it there
            return;
        }


        System.out.println("Shop - Habitats:");
        System.out.printf("%-17s %s\n", "Habitat:", "   Price:");

        // for all the habitats in the shops arraylist getHabitatsForSale it goes through each one
        for (int i = 0; i < shop.getHabitatsForSale().size(); i++) {
            // and gets them one buy one temporarily as h
            Habitat h = shop.getHabitatsForSale().get(i);
            // prints the "current" habitat in the for loops name and price
            System.out.print(i + 1 + ". ");
            System.out.printf("%-17s %s\n", h.getHabitatName(), "" + h.getPrice());
        }
        System.out.println((shop.getHabitatsForSale().size() + 1) + ". Back");
        System.out.print("Choose an option: ");

        String answer = scanner.nextLine();

        int intChoice;

        // trycatch to make sure user input is an int
        try {
            // tries to parse input
            intChoice = Integer.parseInt(answer);
            // if user chose back it ends
            if (intChoice == (shop.getHabitatsForSale().size() + 1)) {
                return;
                // if they chose one of the habitat options the chosen habitat in the arraylist based on the choice, will be saved in chosenHabitat
            } else if (intChoice >= 1 && intChoice <= shop.getHabitatsForSale().size()) {
                Habitat chosenHabitat = shop.getHabitatsForSale().get(intChoice - 1);
                // calls the buyhabitat method for chosenHabitat
                shop.buyHabitat(zoo, chosenHabitat);
            } else {
                System.out.println("Please enter a number between (1-" + (shop.getHabitatsForSale().size() + 1) + ")");
            }
        } catch (Exception e) {
            // if player didnt enter an int
            System.out.println("Please enter a number between (1-" + (shop.getHabitatsForSale().size() + 1) + ")");
        }
    }

    private void buyCreatureMenu(Scanner scanner, Shop shop, Zoo zoo) {
        System.out.println();

        if (shop.getCreaturesForSale().isEmpty()) {
            System.out.println("No creatures available for purchase.");
            return;
        }

        System.out.println("Shop - Creatures:");
        System.out.printf("%-17s %-17s %s\n", "Creature:", "   Price:", "   Danger:");

        // works as the buyHabitatsMethod, for loop does the same but goes through creatures in creaturesFor sale
        for (int i = 0; i < shop.getCreaturesForSale().size(); i++) {
            Creature c = shop.getCreaturesForSale().get(i);
            System.out.print(i + 1 + ". ");
            System.out.printf("%-17s %-17s %s\n", c.getCreatureName(), "" + c.getPrice(), ""+ c.getDangerLevel());
        }
        System.out.println((shop.getCreaturesForSale().size() + 1) + ". Back");
        System.out.print("Choose an option: ");
        String answer = scanner.nextLine();
        int intChoice;
        // trycatch to make sure user enters an int, same if statement options but for creatures
        try {
            intChoice = Integer.parseInt(answer);
            if (intChoice == (shop.getCreaturesForSale().size() + 1)) {
                return;
            } else if (intChoice >= 1 && intChoice <= shop.getCreaturesForSale().size()) {
                Creature chosenCreature = shop.getCreaturesForSale().get(intChoice - 1);

                shop.buyCreature(zoo, chosenCreature);
            } else {
                System.out.println("Please enter a number between (1-" + (shop.getCreaturesForSale().size() + 1) + ")");
            }
        } catch (Exception e) {
            System.out.println("Please enter a number between (1-" + (shop.getCreaturesForSale().size() + 1) + ")");
        }
    }

    private void buyGuardMenu(Scanner scanner, Guard guard, Shop shop, Zoo zoo) {
        System.out.println();
        System.out.println("Shop - Items:");

        System.out.printf("%-12s %s\n", "Guard:", "   Price:");
        System.out.print("1. ");
        System.out.printf("%-12s %s\n", "Guard", guard.getTotalPrice(zoo));
        System.out.println("2. Back");
        System.out.print("Choose an option (1/2): ");

        String answer = scanner.nextLine().toLowerCase();
        // switch case for playre input
        switch (answer) {
            case "1":
                // calls the buyGuard method in shop
                shop.buyGuard(zoo, guard);
            case "2":
                return;
            default:
                System.out.println();
        }
    }

    private void buyUpgradesMenu(Scanner scanner, Shop shop, Zoo zoo, Inventory inventory) {
        System.out.println();
        System.out.println("Upgrades:");
        System.out.println("1. Zoo");
        System.out.println("2. Habitat upgrades");
        System.out.println("3. Creature upgrades");
        System.out.println("4. Back");
        System.out.print("Choose an option: ");

        String answer = scanner.nextLine().toLowerCase();
        System.out.println();

        // switch case which calls corresponding methods
        switch (answer) {
            case "1":
                shop.buyUpgradeZoo(scanner, zoo, inventory);
                break;
            case "2":
                upgradeHabitatMenu(zoo, scanner, shop, inventory);
                break;
            case "3":
                upgradeCreatureMenu(zoo, scanner, shop, inventory);
                break;
            case "4":
                return;
            default:
                System.out.println("Please enter (1/2/3/4)");
        }
    }

    public void upgradeHabitatMenu(Zoo zoo, Scanner scanner, Shop shop, Inventory inventory) {
        System.out.println("Owned Habitats:");
        System.out.printf("%-17s %-15s %-19s %s\n", "Habitat:", "   Level:", "   Status:", "   Creatures:");

        // for loop which goes all the Habitats in the zoo
        for (int i = 0; i < zoo.getHabitats().size(); i++) {
            Habitat h = zoo.getHabitats().get(i);

            // saves some information about the Habitats in the foorloop in variables
            String habitatName = h.getHabitatName();
            int level = h.getHabitatLevel();
            int totalUsedLevel = h.getTotalLevelInHabitat();
            ArrayList<Creature> creatures = h.getCreatures();

            // This I Stringbuilder haven't done myself, I just wanted this in the project for aesthetic reasons
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
            // checks if the totalLevelUsed within the habitat is the same as its level, aka full.
            if (totalUsedLevel == level) {
                status = "\033[31mFull\033[0m               ";
            } else {
                status = "Not Full           ";
            }

            System.out.print(i + 1 + ". ");
            // prints out the information for each on in the for loop
            System.out.printf("%-17s %-15s %-15s %s\n", habitatName, level, status, creatureList);
        }

        System.out.println((zoo.getHabitats().size() + 1) + ". Back");
        System.out.print("Choose an option (1-" + (zoo.getHabitats().size() + 1) + "): ");

        String answer = scanner.nextLine();
        System.out.println();
        int intChoice;
        // trycatch again,  if statment with the same function
        try {
            intChoice = Integer.parseInt(answer);
            if (intChoice == (zoo.getHabitats().size() + 1)) {
                return;
            } else if (intChoice >= 1 && intChoice <= zoo.getHabitats().size()) {
                // saves the users chosen habitat in a Habitat called chosenHabitat
                Habitat chosenHabitat = zoo.getHabitats().get(intChoice - 1);

                // that chosenHabitat is used as a parameter in the shops method buyHabitatUpgrade
                shop.buyHabitatUpgrade(chosenHabitat, zoo, inventory);
            } else {
                System.out.println("Please enter a number between (1-" + (zoo.getHabitats().size() + 1) + ")");
            }
        } catch (Exception e) {
            System.out.println("Please enter a number between (1-" + (zoo.getHabitats().size() + 1) + ")");
        }
    }


    public void upgradeCreatureMenu(Zoo zoo, Scanner scanner, Shop shop, Inventory inventory){
        System.out.println("Owned Creatures:");
        System.out.printf("%-17s %-15s %s\n", "Creature:", "   Level:", "   Habitat:");

        // for loop again, explained above
        for (int i = 0; i < zoo.getCreatures().size(); i++) {
            Creature c = zoo.getCreatures().get(i);

            String creatureName = c.getCreatureName();
            int level = c.getCreatureLevel();
            Habitat belongsToHabitat = c.getHabitat();

            System.out.print(i + 1 + ". ");
            System.out.printf("%-17s %-15s %s\n", creatureName, level, belongsToHabitat.getHabitatName()+ " - lvl "+belongsToHabitat.getHabitatLevel());
        }

        System.out.println((zoo.getCreatures().size()+1)+". Back");
        System.out.print("Choose an option (1-"+(zoo.getCreatures().size()+1)+"): ");

        String answer = scanner.nextLine();
        System.out.println();

        int intChoice;
        // try catch explained above, if statements explained above
        try {
            intChoice = Integer.parseInt(answer);
            if (intChoice == (zoo.getCreatures().size() +1)){
                return;
            }
            else if (intChoice >= 1 && intChoice <= zoo.getCreatures().size()){
                Creature chosenCreature = zoo.getCreatures().get(intChoice-1);

                Habitat chosenCreaturesHabitat = chosenCreature.getHabitat();

                if (shop.canUpgradeCreature(chosenCreature)){
                    shop.buyCreatureUpgrade(chosenCreature, zoo, inventory);
                }
                else{
                    System.out.println("The "+chosenCreaturesHabitat.getHabitatName()+" is too low level. Cannot upgrade "+ chosenCreature.getCreatureName()+".");
                }

            }
            else{
                System.out.println("Please enter a number between (1-"+(zoo.getCreatures().size() +1) +")");
            }
        } catch (Exception e){
            System.out.println("Please enter a number between (1-"+(zoo.getCreatures().size() +1) +")");
        }

    }
}
