import java.sql.Array;
import java.util.ArrayList;

public class Habitat extends Buyable{
    private int habitatLevel;
    private String habitatName;
    private int creatureTotalLevelLimit;
    private int totalLevelInHabitat;
    private ArrayList<Creature> creatures;
    private Biome habitatBiome;

    public Habitat(double price, String habitatName, int habitatLevel, int creatureLevelLimit, Biome habitatBiome, int totalLevelInHabitat){
        super(price);
        this.habitatName = habitatName;
        this.habitatLevel = habitatLevel;
        this.creatureTotalLevelLimit = creatureLevelLimit;
        this.habitatBiome = habitatBiome;
        creatures = new ArrayList<>();
        this.totalLevelInHabitat = totalLevelInHabitat;
    }

    public Biome getHabitatBiome() {
        return habitatBiome;
    }

    public int getTotalLevelInHabitat() {
        return totalLevelInHabitat;
    }

    public void setTotalLevelInHabitat(int totalLevelInHabitat) {
        this.totalLevelInHabitat = totalLevelInHabitat;
    }

    public int getHabitatLevel() {
        return habitatLevel;
    }

    public boolean canAddCreatureToHabitat(Creature creature, Zoo zoo){
        if (habitatBiome.BiomesAreCompatible(creature) && totalLevelInHabitat < creatureTotalLevelLimit && !creatures.contains(creature) && !zoo.getCreatures().contains(creature)){
            return true;
        }
        else{
            return false;
        }
    }

    public void addCreatureToHabitatAndZoo(Creature creature, Zoo zoo){
        if (canAddCreatureToHabitat(creature, zoo)){
            creature.setHabitat(this);
            creatures.add(creature);
            totalLevelInHabitat += creature.getCreatureLevel();
            zoo.addCreatureToZoo(creature);
        }
        else{
            System.out.println("Could not add "+ creature.getCreatureName()+" to "+ this.getHabitatName()+".");

        }
    }

    public ArrayList<Creature> getCreatures() {
        return creatures;
    }

    public int getCreatureLevelLimit() {
        return creatureTotalLevelLimit;
    }



    public String getHabitatName() {
        return habitatName;
    }

    public double getUpgradeCost(){
        double upgradeCost = getPrice()*habitatLevel*1;
        return upgradeCost;
    }

    public int getIntWoodNeeded(){
        int woodNeeded = habitatLevel * 10;
        return woodNeeded;
    }

    public boolean canUpgrade(String recourseUsed, Zoo zoo) {
        switch (recourseUsed.toLowerCase()) {
            case "money":
                return zoo.getMoney() >= getUpgradeCost();
            default:
                return false;
        }
    }

    // When the upgrade method is called upon, the habitat level and total level limit will increase by 1
    public void upgrade(){
        habitatLevel++;
        creatureTotalLevelLimit++;
    }

    public void upgradeReduceMoney(Zoo zoo){
        zoo.setMoney(zoo.getMoney()-getUpgradeCost());
        System.out.println("Upgrade bought for "+ getUpgradeCost()+" coins. "+zoo.getMoney()+" coins remaining.");

    }

    public void tryGenerateItem(){

    }

}
