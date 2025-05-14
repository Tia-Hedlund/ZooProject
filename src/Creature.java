public class Creature extends Buyable{
    private int dangerLevel;
    private String creatureName;
    private int creatureLevel;
    private int pacifyLevel;
    public int dailyProfit;
    private double creatureGoldBonus = 1.0;
    private Biome creatureBiome;

    public Creature(Double price, Biome creatureBiome, int dangerLevel, int pacifyLevel, String creatureName, int level, int dailyProfit, int creatureGoldBonus){
        super(price);
        this.creatureBiome = creatureBiome;
        this.dangerLevel = dangerLevel; // ska randomisa dangerLevelen
        this.pacifyLevel = pacifyLevel;
        this.creatureName = creatureName;
        this.creatureLevel = level;
        this.dailyProfit = dailyProfit;// randomize mellan intervall
        this.creatureGoldBonus = creatureGoldBonus;

    }

/*
Ändra hur securitylevel funkar och ökar för upgrade av guards - upgrade av guards ska göra så security level ökar i zoo och guard level ökar.
(level kan bli gemensam för alla upgradables på samma sätt som pris är för buyable, men då måste upgradable extenda buyable också)

    public boolean attemptEscape(Zoo zoo){
        if (!(zoo.getSecurityLevel() < (this.dangerLevel-this.pacifyLevel){
            return false; // returnar false för attempted Escape lyckades inte
        }
        else{
            return true; // returnar true för att attempted Espace lyckades
        }
    }
*/

    public Biome getCreatureBiome() {
        return creatureBiome;
    }

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

    public void pacify(){
        pacifyLevel++;
    }

    public void updateGoldBonus(Fish fish){
        this.creatureGoldBonus= this.creatureGoldBonus + fish.getGoldBonusValue();
    }

    // move to Zoo
    public void claimProfit(Zoo zoo){
        double profitMoney = this.dailyProfit * this.dangerLevel * this.creatureGoldBonus + 50*this.creatureLevel; // multiplicerar det tidigare värdet på money med danger
        double totalMoney = profitMoney + zoo.getMoney();
        Zoo.setMoney(totalMoney);
        System.out.println(creatureName + " generated "+totalMoney + " in ");
    }
}
