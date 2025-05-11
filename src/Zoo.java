import java.util.ArrayList;
import java.util.Scanner;

public class Zoo {
    public String name;
    private static double money = 0;
    public int maxStorage = 10;
    private int securityLevel = 0;
    private int zooLevel = 1;
    // public ArrayList<Tradables> tradables;

    public int getSecurityLevel() {
        return securityLevel;
    }

    public Zoo(String name){
        this.name = name;
    }

    public static void setMoney(double money) {
        Zoo.money = money;
    }

    public double getMoney() {
        return money;
    }

    public void upgradeMenu(Inventory inventory){
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Choose a method to upgrade the Zoo [1/2/3]");
        System.out.println("1. Use wood");
        System.out.println("2. Pay money");
        System.out.println("3. Back.");

        String choice = myScanner.nextLine();
        switch (choice){
            case "1":
                UpgradeZooWood(inventory);
                break;
            case "2":
                // UpgradeZooMoney();
                break;
            case "3":
                break;
            default:
                System.out.println("Invalid choice, please enter: [1/2/3]");
        }

    }

    public void UpgradeZooWood(Inventory inventory){
        double woodNeeded = this.zooLevel+this.zooLevel*0.5;
        int intWoodNeeded = (int) Math.round(woodNeeded);

        if (inventory.woodCount >= intWoodNeeded) {
            this.zooLevel++;
            System.out.println("Zoo have been updraded to level "+zooLevel);
        }
        else{
            System.out.println(inventory.woodCount+"/"+intWoodNeeded+ ". Not enough wood. "+(intWoodNeeded-inventory.woodCount)+ " more wood required.");
        }

    }


}
