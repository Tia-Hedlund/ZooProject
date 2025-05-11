public class Creature {
    private int dangerLevel;
    public int price;
    private String creatureName;
    private int creatureLevel;

    public Creature(int dangerLevel, int price, String creatureName, int level){
        this.dangerLevel = dangerLevel;
        this.price = price;
        this.creatureName = creatureName;
        this.creatureLevel = level;
    }
    //public Biome creatureBiome;
    public boolean attemptEscape(Zoo zoo){
        if (!(zoo.getSecurityLevel() < this.dangerLevel)){
            return false; // returnar false för attempted Escape lyckades inte
        }
        else{
            return true; // returnar true för att attempted Espace lyckades
        }
    }

    public int getCreatureLevel() {
        return creatureLevel;
    }

    public int getDangerLevel() {
        return dangerLevel;
    }

    public String getCreatureName() {
        return creatureName;
    }

    public void feed(){
        this.creatureLevel++;
    }

    public static int claimProfit(Zoo zoo){
        //Zoo.getMoney();

        return -4;
    }
}
