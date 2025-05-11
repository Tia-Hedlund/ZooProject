public class Creature {
    private int dangerLevel;
    public int price;
    public String creatureName;
    private int level;

    public Creature(int dangerLevel, int price, String creatureName, int level){
        this.dangerLevel = dangerLevel;
        this.price = price;
        this.creatureName = creatureName;
        this.level = level;
    }
    //public Biome creatureBiome;
    public boolean attemptEscape(int securityLevel){
        if (!(securityLevel < this.dangerLevel)){
            return false; // returnar false för attempted Escape lyckades inte
        }
        else{
            return true; // returnar true för att attempted Espace lyckades
        }
    }

    public int getLevel() {
        return level;
    }

    public int getDangerLevel() {
        return dangerLevel;
    }

    public void upgrade(){
        this.level++;
    }

    public static int claimProfit(int money, int dangerLevel){
        money = Zoo.getMoney();

        return -4;
    }
}
