import java.util.Scanner;

import static java.lang.Thread.*;

public class ZooGame {
    public void StartZoo()
    {
        int day = 0;
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
        Biome woodlandBiome = new WoodlandBiome();
        Habitat meadow = new Meadow(100.0, "Meadow", 1, 1, woodlandBiome, 0);
        Creature twiglet = new Creature(100.0, woodlandBiome, 1, 0, "Twiglet", 1, 10, 1);

        zoo.addHabitatToZoo(meadow);

        // lägg till det i klassen istället för här
        if (meadow.canAddCreatureToHabitat(twiglet, zoo)){
            meadow.addCreatureToHabitatAndZoo(twiglet, zoo);
        }
        else{
            System.out.println("Could not add "+ twiglet.getCreatureName()+" to "+ meadow.getHabitatName()+".");
        }

        // start of loop
        day +=1;

        System.out.println();
        zoo.printZooStats();
        zoo.printHabitatStats();

        // Make it so that a certain zoo level is required to unluck certain habitats and creatures.  !!!!!

        System.out.println("The creatures you own ("+twiglet.getCreatureName()+") will generate coins which you can collect at the end of each day.");
        System.out.println("The coins will be added to your Zoo's total coins (Current coins: "+zoo.getMoney()+")");
        System.out.println("Moreover, the coins can be used to buy 'Items', 'Habitats' and more 'Creatures' in the 'Shop'.");
        System.out.println("Or to upgrade the level of your Zoo, guards, owned habitats or creatures. ");
        System.out.println("More information can be found under the 'Information' option when choosing to 'Upgrade>' or a 'Buyable' in the 'Shop'. ");
        System.out.println();

        boolean playing = true;
        while (playing){
            System.out.println("✦ʚ♡ɞ✦ Day "+ day +" ✦ʚ♡ɞ✦" );
            boolean dayActive = true;

            // if showMainMenu returns false the boolean playing will become false which ends the loop
            playing = showMainMenu(myScanner, zoo, day);

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

    private boolean showMainMenu(Scanner scanner, Zoo zoo, int day){
        System.out.println();
        System.out.println("Zoo Actions: ");
        System.out.println();
        System.out.println("1. Visit Shop");
        System.out.println("2. View Creatures and Habitats");
        System.out.println("3. End Day");
        System.out.println("4. Quit Game");
        System.out.print("Choose an action: (1/2/3/4):");

        String stringAnswer = scanner.nextLine();
        String answer = stringAnswer.toLowerCase();
        switch (answer){
            case "1":
                visitShopMenu(scanner, zoo, day);
                break;
            case "2":
                zoo.printHabitatStats();
                break;
            case "3":
                endDay(zoo);
                day++;
                break;
            case "4":
                System.out.println("Quitting game.");
                return false;
            default:
                System.out.println("Please enter 1/2/3/4.");
        }
        return true;
    }

    private void visitShopMenu(Scanner scanner, Zoo zoo, int day){
        System.out.println();
        System.out.println("Shop:");
        System.out.println();
        System.out.println("1. Items");
        System.out.println("2. Habitats");
        System.out.println("3. Creatures");
        System.out.println("4. Guards");
        System.out.println("5. Upgrades");
        System.out.println("6. Sell Items");
        System.out.println("7. Back");
        System.out.print("Choose an option (1/2/3/4): ");

        String answer = scanner.nextLine().toLowerCase();

        switch (answer){
            case "1":
                break;
            case "2":
                break;
            case "3":
                break;
            case "4":
                break;
            case "5":
                break;
            case "6":
                break;
            case "7":
                showMainMenu(scanner, zoo, day);
                break;
            default:
                System.out.println("Please enter (1/2/3/4/5/6/7)");

        }
    }

    private void endDay(Zoo zoo){
        for (Creature creature : zoo.getCreatures()){
            zoo.claimProfit(creature);
        }
        System.out.println("Total Coins: "+zoo.getMoney());
        // for loop för creatures i zoo, testa att fly för varje
    }

    private void buyItemsMenu(Scanner scanner, Shop shop, Zoo zoo, Inventory inventory){
        System.out.println();
        System.out.println("Shop - Items:");
        System.out.println();
        System.out.println("1. Wood");
        System.out.println("2. Fish");
        System.out.println("3. Food");
        System.out.println("4. Back to Shop Menu");
        System.out.print("Choose an option: (1/2/3/4): ");

        String answer = scanner.nextLine().toLowerCase();
        int quantity = 0;

        switch (answer){
            case "1":
                String item = "wood";
                quantity = enterQuantity(scanner, item);

                shop.buyItems(zoo, item, inventory, quantity);

                break;
            case "2":
                break;
            case "3":
                break;
            case "4":
                break;
            default:
                System.out.println();

        }
    }

    private int enterQuantity(Scanner scanner, String item){
        int quantity = 0;
        boolean intInput = false;

        while (!intInput){
            System.out.print("Enter an number of "+item+" to purchase: ");
            try{
                quantity = scanner.nextInt();
                intInput=true;
            } catch(Exception e){
                System.out.println("Enter a number.");
                scanner.nextLine();
            }
        }

        return quantity;
    }
}
