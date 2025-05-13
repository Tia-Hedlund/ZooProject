import java.util.Scanner;

import static java.lang.Thread.*;

public class ZooGame {
    public void StartZoo()
    {
        int day = 0;
        Scanner myScanner = new Scanner(System.in);
        String firstHabitat = "Forest Habitat";
        String firstCreature= "First Creature";

        System.out.println("\033[31mThis is not a normal Zoo.");
        System.out.println("This is...\033[0m");
        System.out.println("[Please name your zoo]");

        String zooName = myScanner.nextLine();
        System.out.println();

        // Starting Zoo and Receiving starting biome and creature:
        Zoo zoo = new Zoo(zooName, 100, 50, 0, 1);
        Biome woodlandBiome = new WoodlandBiome();
        Habitat meadow = new Meadow(100.0, "Meadow", 1, 1, woodlandBiome, 0);
        Creature twiglet = new Creature(100.0, woodlandBiome, 1, 0, "Twiglet", 1, 10, 1);

        zoo.addHabitatToZoo(meadow);
        if (meadow.canAddCreatureToHabitat(twiglet, zoo)){
            meadow.addCreatureToHabitatAndZoo(twiglet, zoo);
        }
        else{
            System.out.println("Could not add "+ twiglet.getCreatureName()+" to "+ meadow.getHabitatName()+".");
        }

        // start of loop

        day +=1;
        System.out.println("🧬 Day "+ day + " 🐲" );
        System.out.println();

        zoo.printZooStats();
        zoo.printHabitatStats();





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
}
