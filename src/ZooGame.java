import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Thread.*;

public class ZooGame {
    public void StartZoo()
    {
        int day = 1;
        Scanner myScanner = new Scanner(System.in);
        /*
        System.out.println("\033[31mThis is not a normal Zoo.");
        System.out.println("This is...\033[0m");
        */

        System.out.println("[Please name your zoo]");


        String zooName = myScanner.nextLine();
        System.out.println();

        // Starting Zoo and Receiving starting biome and creature:
        Zoo zoo = new Zoo(zooName, 100, 50, 0, 1);
        Shop shop = new Shop();
        Fish fish = new Fish(50.0);
        Fruit fruit = new Fruit(50.0);
        Wood wood = new Wood(50.0);
        Inventory inventory = new Inventory(0);
        Guard guard = new Guard(50.0);

        Biome woodlandBiome = new WoodlandBiome();
        Biome drylandBiome = new DrylandBiome();
        Biome oceanBiome = new OceanBiome();

        Habitat meadow = new Meadow(100.0, "Meadow", 1, 1, woodlandBiome, 0);
        Habitat forest = new Forest(100.0,"Forest", 1,1, woodlandBiome,0);
        Habitat desert = new Desert(100.0,"Desert", 1,1, drylandBiome, 0);
        Habitat savannah = new Savannah(100.0,"Savannah", 1,1, drylandBiome, 0);
        Habitat coralReef = new CoralReef(100.0,"Coral Reef", 1,1, oceanBiome, 0);
        Habitat glacier = new Glacier(100.0,"Glacier", 1,1, oceanBiome, 0);

        shop.addHabitatForSale(forest);
        shop.addHabitatForSale(desert);
        shop.addHabitatForSale(savannah);
        shop.addHabitatForSale(coralReef);
        shop.addHabitatForSale(glacier);

        Creature twiglet = new Creature(100.0, woodlandBiome, 1, 0, "Twiglet", 1, 10, 1);
        Creature reek = new Creature(100.0, drylandBiome, 1, 0, "Reek", 1, 10, 1);
        Creature scavenger = new Creature(100.0, drylandBiome, 1, 0, "Scavenger", 1, 10, 1);
        Creature sawtooth = new Creature(100.0, woodlandBiome, 1, 0, "Sawtooth", 1, 10, 1);

        shop.addCreatureForSale(reek);
        shop.addCreatureForSale(scavenger);
        shop.addCreatureForSale(sawtooth);

        zoo.addHabitatToZoo(meadow);
        meadow.addCreatureToHabitatAndZoo(twiglet, zoo);

        System.out.println();
        zoo.printZooStats();
        zoo.printHabitatStats();

        // Make it so that a certain zoo level is required to unluck certain habitats and creatures.  !!!!!

        System.out.println("The creatures you own ("+twiglet.getCreatureName()+") will generate coins which you can collect at the end of each day.");
        System.out.println("The coins will be added to your Zoo's total coins (Current coins: "+zoo.getMoney()+")");
        System.out.println("Moreover, the coins can be used to buy 'Items', 'Habitats' and more 'Creatures' in the 'Shop'.");
        System.out.println("Or to upgrade the level of your Zoo, guards, owned habitats or creatures. ");
        System.out.println("More information can be found under the 'Information' option when choosing to 'Upgrade>' or a 'Buyable' in the 'Shop'. ");

        boolean playing = true;
        while (playing){
            System.out.println();
            System.out.println("✦ʚ♡ɞ✦ Day "+ day +" ✦ʚ♡ɞ✦" );
            boolean dayActive = true;
            while (dayActive){
                // if showMainMenu returns false the boolean "dayActive" will become false which ends the loop
                dayActive = showMainMenu(myScanner,  zoo,  day,  shop,  inventory,  wood,  fish,  fruit, guard);

            }
            zoo.nightTime(shop);
            endDay(zoo, day);
            day++;




        }



        // Shop switch case being divided into : 1 items, 2 habitats, 3 creatures, 4 upgrades, 5 guards, 6 sell, 7 back, default
        // Upgrades a switch case divided into : 1 upgrade Zoo, 2 upgrade habitat, 3 upgrade creature, 4 upgrade guards, 5 back

        // let user try to upgrade their Twiglet, and then recommend the user to end the day and claim profit.
        // With the profit the second day, recommend the user to buy some guards to increase the security level, because otherwise their creature might try to escape.
        // "Zoo is now ready to get run on its own" Good luck!

        // Gör så Zoo Habitat och Creature och kanske guard också ärver från en ny klass kallad "Upgradable" som alla har en polimorfimetod som används för att upgrade den saken.


        // få igång spelet, faktiskt skapa habitats och flera creatures.
        // gräs monster grön text, gräs habitats grön och bold
        // Dryland monster orange text, dryland habitats orange och bold
        // Ocean monster blue text, ocean habitat blue and bold.
        // stå såhär : (lägg också till så att det är så det står i början istället för all text.
        /*
        Habitat:    Level   Creatures:
        Coral Reef  2       [Bubblebo - lvl 1, Ocelian - lvl 3]
        Forest      1       [Timber - lvl 1]
        Savannah    3       []                                      (inga monster)

        Röd text när någon försökt fly. Kanske att man måste betala en qouta efter dagen, och att någon flytt kommer göra att du måste betala rejält vilket kan köra att du inte kan betala quouta.
        */
    }

    private boolean showMainMenu(Scanner scanner, Zoo zoo, int day, Shop shop, Inventory inventory, Wood wood, Fish fish, Fruit fruit, Guard guard){
        System.out.println();
        System.out.println("Zoo Actions: ");
        System.out.println();
        System.out.println("1. Visit Shop");
        System.out.println("2. View Creatures and Habitats");
        System.out.println("3. View Zoo stats");
        System.out.println("4. Use Item");
        System.out.println("5. End Day");
        System.out.println("6. Quit Game");
        System.out.print("Choose an action: (1/2/3/4): ");

        String stringAnswer = scanner.nextLine();
        String answer = stringAnswer.toLowerCase();
        System.out.println();
        switch (answer){
            case "1":
                visitShopMenu(scanner, zoo, day, shop, inventory, wood, fish, fruit, guard);
                break;
            case "2":
                zoo.printHabitatStats();
                break;
            case "3":
                zoo.printZooStats();
                break;
            case "4":
                // useItem
                break;
            case "5":
                return false;
            case "6":
                System.out.println("Quitting game.");
                System.exit(0);
            default:
                System.out.println("Please enter 1/2/3/4.");
        }
        return true;
    }

    private void endDay(Zoo zoo, int day){
        System.out.println("Ending day "+ day + ".");
        for (Creature creature : zoo.getCreatures()){
            zoo.claimProfit(creature);
        }
        System.out.println("Total Coins: "+zoo.getMoney());
    }

    private void useItemMenu(Scanner scanner, Inventory inventory){
        System.out.println();
        System.out.printf("%-17s %s\n", "Item:", "In inventory:");
        System.out.print("1. ");
        System.out.printf("%-17s %s\n", "Wood", inventory.getItems().getOrDefault("wood", 0));
        System.out.print("2. ");
        System.out.printf("%-17s %s\n", "Fish", inventory.getItems().getOrDefault("fish", 0));
        System.out.print("3. ");
        System.out.printf("%-17s %s\n", "Fruit", inventory.getItems().getOrDefault("fruit", 0));
        System.out.println();
        System.out.println("4. Back");
        System.out.print("Choose an option: ");

        String stringAnswer = scanner.nextLine();
        String answer = stringAnswer.toLowerCase();
        System.out.println();
        switch (answer){
            case "1":

                break;
            case "2":

                break;
            case "3":

                break;
            case "4":
                return;
            default:
        }
    }

    private void useItemMenu(){

    }

    private void visitShopMenu(Scanner scanner, Zoo zoo, int day, Shop shop, Inventory inventory, Wood wood, Fish fish, Fruit fruit, Guard guard){
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

        switch (answer){
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
                // Sell Items Menu
                break;
            case "7":
                return;
            default:
                System.out.println("Please enter (1/2/3/4/5/6/7)");

        }
    }

    private void buyItemsMenu(Scanner scanner, Shop shop, Zoo zoo, Inventory inventory, Wood wood, Fish fish, Fruit fruit, int day){
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
        System.out.println();
        System.out.println("4. Back");
        System.out.print("Choose an option: (1/2/3/4): ");

        String answer = scanner.nextLine().toLowerCase();
        int quantity = 0;
        String item = "";

        switch (answer){
            case "1":
                item = wood.getClass().getSimpleName().toLowerCase();
                quantity = enterQuantity(scanner, item);
                shop.buyItems(zoo, wood, inventory, quantity);
                break;
            case "2":
                item = fish.getClass().getSimpleName().toLowerCase();
                quantity = enterQuantity(scanner, item);
                shop.buyItems(zoo, fish, inventory, quantity);
                break;
            case "3":
                item = fruit.getClass().getSimpleName().toLowerCase();
                quantity = enterQuantity(scanner, item);
                shop.buyItems(zoo, fruit, inventory, quantity);
                break;
            case "4":
                return;
            default:
                System.out.println();

        }
    }

    private int enterQuantity(Scanner scanner, String item){
        int quantity = 0;
        boolean intInput = false;

        while (!intInput){
            System.out.print("Enter a number of "+item+" to purchase: ");
            try{
                quantity = scanner.nextInt();
                scanner.nextLine();
                intInput=true;
            } catch(Exception e){
                System.out.println("Enter a number.");
                scanner.nextLine();
            }
        }

        return quantity;
    }

    private void buyHabitatsMenu(Scanner scanner, Shop shop, Zoo zoo) {
        System.out.println();

        if (shop.getHabitatsForSale().isEmpty())
        {
            System.out.println("No habitats available for purchase.");
            return;
        }

        System.out.println("Shop - Habitats:");
        System.out.printf("%-17s %s\n", "Habitat:", "   Price:");

        for (int i = 0; i < shop.getHabitatsForSale().size(); i++){
            Habitat h = shop.getHabitatsForSale().get(i);
            System.out.print(i+1 +". ");
            System.out.printf("%-17s %s\n", h.getHabitatName(), ""+h.getPrice() );
        }
        System.out.println((shop.getHabitatsForSale().size() +1) +". Back");
        System.out.print("Choose an option: ");

        String answer = scanner.nextLine();

        int intChoice;

        try {
            intChoice = Integer.parseInt(answer);
            if (intChoice == (shop.getHabitatsForSale().size() +1)){
                return;
            }
            else if (intChoice >= 1 && intChoice <= shop.getHabitatsForSale().size()){
                Habitat chosenHabitat = shop.getHabitatsForSale().get(intChoice-1);
                shop.buyHabitat(zoo, chosenHabitat);
            }
            else{
                System.out.println("Please enter a number between (1-"+(shop.getHabitatsForSale().size() +1) +")");
            }
        } catch (Exception e){
            System.out.println("Please enter a number between (1-"+(shop.getHabitatsForSale().size() +1) +")");
        }
    }

    private void buyCreatureMenu(Scanner scanner, Shop shop, Zoo zoo) {
        System.out.println();

        if (shop.getCreaturesForSale().isEmpty())
        {
            System.out.println("No creatures available for purchase.");
            return;
        }

        System.out.println("Shop - Creatures:");
        System.out.printf("%-17s %s\n", "Creature:", "   Price:");

        for (int i = 0; i < shop.getCreaturesForSale().size(); i++){
            Creature c = shop.getCreaturesForSale().get(i);
            System.out.print(i+1 +". ");
            System.out.printf("%-17s %s\n", c.getCreatureName(), ""+c.getPrice() );
        }
        System.out.println((shop.getCreaturesForSale().size() +1) +". Back");
        System.out.print("Choose an option: ");
        String answer = scanner.nextLine();
        int intChoice;
        try {
            intChoice = Integer.parseInt(answer);
            if (intChoice == (shop.getCreaturesForSale().size() +1)){
                return;
            }
            else if (intChoice >= 1 && intChoice <= shop.getCreaturesForSale().size()){
                Creature chosenCreature = shop.getCreaturesForSale().get(intChoice-1);

                shop.buyCreature(zoo, chosenCreature);
            }
            else{
                System.out.println("Please enter a number between (1-"+(shop.getCreaturesForSale().size() +1) +")");
            }
        } catch (Exception e){
            System.out.println("Please enter a number between (1-"+(shop.getCreaturesForSale().size() +1) +")");
        }
    }

    private void buyGuardMenu(Scanner scanner, Guard guard, Shop shop, Zoo zoo){
        System.out.println();
        System.out.println("Shop - Items:");

        System.out.printf("%-12s %s\n", "Guard:", "   Price:");
        System.out.print("1. ");
        System.out.printf("%-12s %s\n", "Guard", guard.getTotalPrice(zoo));
        System.out.println("2. Back");
        System.out.print("Choose an option (1/2): ");

        String answer = scanner.nextLine().toLowerCase();

        switch (answer){
            case "1":
                shop.buyGuard(zoo, guard);
            case "2":
                return;
            default:
                System.out.println();
        }
    }

    private void buyUpgradesMenu(Scanner scanner, Shop shop, Zoo zoo, Inventory inventory){
        System.out.println();
        System.out.println("Upgrades:");
        System.out.println("1. Zoo");
        System.out.println("2. Habitat upgrades");
        System.out.println("3. Creature upgrades");
        System.out.println("4. Back");
        System.out.print("Choose an option: ");

        String answer = scanner.nextLine().toLowerCase();
        System.out.println();

        switch (answer){
            case "1":
                shop.buyUpgradeZoo(scanner, zoo, inventory);
                break;
            case "2":
                upgradeHabitatMenu(zoo, scanner, shop, inventory);
                break;
            case "3":
                // upgrade creature menu
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

        for (int i = 0; i < zoo.getHabitats().size(); i++) {
            Habitat h = zoo.getHabitats().get(i);

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
                status = "\033[31mFull\033[0m               ";
            } else {
                status = "Not Full               ";
            }

            System.out.print(i + 1 + ". ");
            System.out.printf("%-17s %-15s %-15s %s\n", habitatName, level, status, creatureList);

            System.out.println((zoo.getHabitats().size()+1)+". Back");
            System.out.print("Choose an option (1-"+(zoo.getHabitats().size()+1)+"): ");

            String answer = scanner.nextLine();
            System.out.println();
            int intChoice;
            try {
                intChoice = Integer.parseInt(answer);
                if (intChoice == (zoo.getHabitats().size() +1)){
                    return;
                }
                else if (intChoice >= 1 && intChoice <= zoo.getHabitats().size()){
                    Habitat chosenHabitat = zoo.getHabitats().get(intChoice-1);

                    shop.buyHabitatUpgrade(chosenHabitat, zoo, inventory);
                }
                else{
                    System.out.println("Please enter a number between (1-"+(shop.getCreaturesForSale().size() +1) +")");
                }
            } catch (Exception e){
                System.out.println("Please enter a number between (1-"+(shop.getCreaturesForSale().size() +1) +")");
            }
        }
    }

    public void upgradeCreatureMenu(){

    }
}
