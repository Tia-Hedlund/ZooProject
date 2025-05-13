import java.util.Scanner;

import static java.lang.Thread.*;

public class ZooGame {
    public void StartZoo()
    {
        Scanner myScanner = new Scanner(System.in);
        System.out.println("\033[31mThis is not a normal Zoo.");
        System.out.println("This is...\033[0m");
        System.out.println("[Please name your zoo]");

        String zooName = myScanner.nextLine();
        String firstHabitat = "Forest Habitat";
        String firstCreature= "First Creature";
        int day = 1;

        Zoo TheZoo = new Zoo(zooName, 100, 50, 0, 1);

        System.out.println("Day "+day);
        TheZoo.printZooStats();
        myScanner.nextLine();

        System.out.println("Lets start off by buying your first habitat.");
        System.out.println("Since this is your first day, you will be given a " + firstHabitat + " for free!");


        System.out.println("You have recieved a "+ firstHabitat + ".");
        System.out.println("Press enter to construct.");
        myScanner.nextLine();
        System.out.println(firstHabitat+ "has been constructed");
        System.out.println();
        System.out.println("Now let's buy your first creature.");
        myScanner.nextLine();



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
