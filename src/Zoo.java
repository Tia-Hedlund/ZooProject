import java.util.ArrayList;
import java.util.Scanner;

public class Zoo {
    public String name;
    private static double money;
    public int maxStorage;
    private int securityLevel = 0;
    private int zooLevel = 1;
    // public ArrayList<Tradables> tradables;

    public Zoo(String name, double money, int maxStorage, int securityLevel, int zooLevel){
        this.name = name;
        this.money = money;
        this.maxStorage = maxStorage;
        this.securityLevel = securityLevel;
        this.zooLevel = zooLevel;
    }

    public int getSecurityLevel() {
        return securityLevel;
    }

    public static void setMoney(double money) {
        Zoo.money = money;
    }

    public double getMoney() {
        return money;
    }

    public void printZooStats(){
        System.out.println(this.name + " Stats:");
        System.out.println("Coins: "+ this.money);
        System.out.println("Level: "+ this.zooLevel);
        System.out.println("Security Level: " + this.securityLevel);
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
                UpgradeZooMoney();
                break;
            case "3":
                break;
            default:
                System.out.println("Invalid choice, please enter: [1/2/3]");
        }

    }

    public void ZooUpgrade(){
        this.zooLevel++;
        maxStorage+=10;
    }

    public void UpgradeZooWood(Inventory inventory){
        double woodNeeded = this.zooLevel+this.zooLevel*0.5;
        int intWoodNeeded = (int) Math.round(woodNeeded);

        if (inventory.woodCount >= intWoodNeeded) {
            ZooUpgrade();
            System.out.println("Zoo have been updraded to level "+zooLevel);
        }
        else{
            System.out.println(inventory.woodCount+"/"+intWoodNeeded+ ". Not enough wood. "+(intWoodNeeded-inventory.woodCount)+ " more wood required.");
        }
    }

    public void UpgradeZooMoney(){
        double upgradeCost = this.zooLevel*10;
        if (this.money>upgradeCost){
            ZooUpgrade();
        }
        else{
            System.out.println(this.money+"/"+upgradeCost +". Not enough money. "+(upgradeCost-this.money)+" more money required.");
        }
    }
}
