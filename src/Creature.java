public class Creature {
    private int dangerLevel;
    public int price;
    private String creatureName;
    private int creatureLevel;
    private int pacifyLevel;
    //public Biome creatureBiome;

    public Creature(int dangerLevel, int pacifyLevel, int price, String creatureName, int level){
        this.dangerLevel = dangerLevel;
        this.pacifyLevel = pacifyLevel;
        this.price = price;
        this.creatureName = creatureName;
        this.creatureLevel = level;
    }

/*
    public boolean attemptEscape(Zoo zoo){
        if (!(zoo.getSecurityLevel() < (this.dangerLevel-this.pacifyLevel){
            return false; // returnar false för attempted Escape lyckades inte
        }
        else{
            return true; // returnar true för att attempted Espace lyckades
        }
    }
*/
    public int getCreatureLevel() {
        return creatureLevel;
    }

    public int getDangerLevel() {
        return dangerLevel;
    }

    public int getPacifyLevel() {
        return pacifyLevel;
    }

    public String getCreatureName() {
        return creatureName;
    }

    public void upgrade(){
        this.creatureLevel++;
    }

    public boolean possibleToPacify(){
        if (this.dangerLevel>=this.pacifyLevel){
            return true; // ja, det är möjligt att pacifya
        }
        else{
            return false; // nej, det är inte möjligt att pacifya
        }
    }

    public void Pacify(){
        pacifyLevel++;
    }

    public void claimProfit(Zoo zoo){
        zoo.getMoney() = zoo.getMoney()*this.dangerLevel();

    }
}
