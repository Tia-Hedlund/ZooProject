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

        Habitat habitat1 = new Habitat(firstHabitat, 1, 1);
        System.out.println("You have recieved a "+ firstHabitat + ".");
        System.out.println("Press enter to construct.");
        myScanner.nextLine();
        System.out.println(firstHabitat+ "has been constructed");
        System.out.println();
        System.out.println("Now let's buy your first creature.");
        myScanner.nextLine();

        Creature creature1 = new Creature(1, 0, 10, firstCreature, 1, 2, 1);
        habitat1.addCreature(creature1);


    }

}
