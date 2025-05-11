import java.util.ArrayList;
public class Zoo {
    public String name;
    private static int money = 0;
    public int maxStorage = 10;
    private int securityLevel = 1;
    // public ArrayList<Tradables> tradables;

    public int getSecurityLevel() {
        return securityLevel;
    }

    public Zoo(String name){
        this.name = name;
    }

    public static int getMoney() {
        return money;
    }

}
