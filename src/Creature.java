public class Creature extends Buyable{
    // creates all the attributes
    private int dangerLevel;
    private String creatureName;
    private int creatureLevel;
    private int pacifyLevel;
    private int dailyProfit;
    private double creatureGoldBonus = 1.0;
    private Biome creatureBiome;
    private Habitat habitat;

    // constructor
    public Creature(Double price, Biome creatureBiome, int dangerLevel, int pacifyLevel, String creatureName, int level, int dailyProfit, int creatureGoldBonus){
        super(price);
        this.creatureBiome = creatureBiome;
        this.dangerLevel = dangerLevel;
        this.pacifyLevel = pacifyLevel;
        this.creatureName = creatureName;
        this.creatureLevel = level;
        this.dailyProfit = dailyProfit;
        this.creatureGoldBonus = creatureGoldBonus;
    }

    // getters and setters
    public Habitat getHabitat() {
        return habitat;
    }

    public void setHabitat(Habitat habitat) {
        this.habitat = habitat;
    }

    public Biome getCreatureBiome() {
        return creatureBiome;
    }

    public int getDailyProfit() {
        return dailyProfit;
    }

    public double getCreatureGoldBonus() {
        return creatureGoldBonus;
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

    public int getFoodRequired(){
        int foodRequired = creatureLevel*10*dangerLevel;
        return foodRequired;
    }

    public double getUpgradeCost(){
        double upgradeCost = getPrice()*creatureLevel*1;
        return upgradeCost;
    }

    // method which returns boolean to check if creature can get upgraded with money
    public boolean canUpgradeMoney(Zoo zoo){
        if (getUpgradeCost()<=zoo.getMoney()){
            return true;
        }
        else{
            System.out.println("Not enough coins to purchase upgrade.");
            return false;
        }
    }

    // method for reducing money if upgrading a creature with money
    public void reduceUpgradeMoney(Zoo zoo){
        zoo.setMoney(zoo.getMoney()-getUpgradeCost());
        System.out.println("Upgrade bought for "+ getUpgradeCost()+". "+zoo.getMoney()+" coins remaining.");
    }

    // upgrades the creatuer and increaes the habitats total level in habitat
    public void upgrade(Habitat habitat){
        this.creatureLevel++;
        habitat.setTotalLevelInHabitat(habitat.getTotalLevelInHabitat()+1);
    }

    // a sort of getter for fruitIntNeededToFeed
    public int fruitNeededToFeed(){
        int fruitNeededToFeed = dangerLevel*10*(pacifyLevel+1);
        return fruitNeededToFeed;
    }

    public int getBonus(){
        int bonus = (int)((creatureGoldBonus-1)/0.1);

        if (bonus == 0){
            return 1;
        }
        else{
            return (bonus+1);
        }
    }

    public int fishNeededToFeed(){
        int fishNeededToFeed = dangerLevel*5*(getBonus());
        return fishNeededToFeed;
    }

    public boolean possibleToPacify(){
        if (dangerLevel>=pacifyLevel){
            return true; // yes possible to pacify
        }
        else{
            return false; // no, cannot pacify
        }
    }

    public void pacify(){
        pacifyLevel++;
    }

    public void updateGoldBonus(Fish fish){
        creatureGoldBonus = creatureGoldBonus + fish.getGoldBonusValue();
    }
}
