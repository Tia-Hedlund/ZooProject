import java.util.ArrayList;
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

    public void upgrade(){
        Scanner myScanner =
        System.out.println("Choose a method to upgrade the Zoo");
        System.out.println("1. Use wood");
        System.out.println("2. Pay money");
        System.out.println("Back.");

        int choice =
        switch ()
        zooLevel++;
    }

}
