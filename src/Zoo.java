import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Zoo {
    private String name;
    private double money;
    private int maxStorage;
    private int securityLevel = 0;
    private int zooLevel = 1;
    private ArrayList<Creature> creatures;
    private ArrayList<Habitat> habitats;
    private int guardCount;

    public Zoo(String name, double money, int maxStorage, int securityLevel, int zooLevel) {
        this.name = name;
        this.money = money;
        this.maxStorage = maxStorage;
        this.securityLevel = securityLevel;
        this.zooLevel = zooLevel;
        this.creatures = new ArrayList<>();
        this.habitats = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Habitat> getHabitats() {
        return habitats;
    }

    public ArrayList<Creature> getCreatures() {
        return creatures;
    }

    public int getMaxStorage() {
        return maxStorage;
    }

    public void addCreatureToZoo(Creature creature) {
        creatures.add(creature);
    }

    public void addHabitatToZoo(Habitat habitat) {
        habitats.add(habitat);
    }

    public int getSecurityLevel() {
        return securityLevel;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getMoney() {
        return money;
    }

    public int getGuardCount() {
        return guardCount;
    }

    public void addGuardToZoo(){
        securityLevel++;
        guardCount++;
        System.out.println("Security Level increased to level "+ securityLevel+".");
    }

    public void nightTime(Shop shop){
        Creature escaped = creatureEscape();
        if (escaped != null){

            System.out.println(escaped.getCreatureName() +" escaped during the night!");
            System.out.println("Your security level ("+ securityLevel+") was too low and could not withstand the danger posed by the creatures ("+getTotalDangerLevel()+").");

            // Remove the creature that escaped from its specific habitat
            Habitat creaturesHabitat = escaped.getHabitat();
            creaturesHabitat.getCreatures().remove(escaped);
            creaturesHabitat.setTotalLevelInHabitat(creaturesHabitat.getTotalLevelInHabitat()-escaped.getCreatureLevel());
            shop.addCreatureForSale(escaped);

            // Remove creature from the zoo
            creatures.remove(escaped);
            // Remove creature from the habitat


        }
        else{
            int diff = securityLevel-getTotalDangerLevel();
            if (creatures.isEmpty()){
                System.out.println(name +" stood empty tonight. Quiet nights have their own charm...");
            }
            if (securityLevel==0){
                System.out.println("Lucky! Even without a single guard on duty no creature escaped. ");
            }
            else if(diff<0 && !(securityLevel==0)){
                System.out.println("The creatures clawed at the gates, but somehow your defenses held... for now.");
            }
            else if(diff>=0 && diff<=3){
                System.out.println("Your guards kept all creatures from escaping tonight.");
            }
            else if(diff>3){
                System.out.println("Guard duty or holiday? With this many on watch, it's hard to tell.");
            }
        }
    }

    public int getTotalDangerLevel(){
        int totalDanger = 0;
        for (Creature c : creatures) {
            int danger = c.getDangerLevel() - c.getPacifyLevel();
            totalDanger += danger;
        }
        return totalDanger;
    }

    public Creature creatureEscape() {
        int totalDanger = getTotalDangerLevel();
        if (securityLevel >= totalDanger) {
            return null;
        } else {

            // Out of the creatures at the zoo, the ones that are not fully pacified are added into a new ArrayList called notPacifiedCreatures
            ArrayList<Creature> nonPacifiedCreatures = new ArrayList<>();
            for (Creature c : creatures) {
                if (c.getDangerLevel() - c.getPacifyLevel() > 0) {
                    nonPacifiedCreatures.add(c);
                }
            }

            if (nonPacifiedCreatures.isEmpty()) {
                // If all creatures are pacified non will escape.
                return null;
            }

            Random myRandom = new Random();
            Creature escapingCreature = nonPacifiedCreatures.get(myRandom.nextInt(nonPacifiedCreatures.size()));

            int randomInt1 = myRandom.nextInt(3);
            int randomInt2 = myRandom.nextInt(3);

            if (randomInt1==randomInt2){
                return escapingCreature;
            }
            else{
                return null;
            }
        }
    }

    public void printZooStats(){
        System.out.printf("%-17s %-15s %s\n", "Coins:", "Zoo Level:", "Security Level:");
        System.out.printf("%-17s %-15s %s\n", money, zooLevel, securityLevel);

        System.out.println();
    }

    public void printHabitatStats(){
        System.out.printf("%-17s %-15s %s\n", "Habitat:", "Level:", "Creatures:");

        for (Habitat habitat : this.habitats){
            String habitatName = habitat.getHabitatName();
            int level = habitat.getHabitatLevel();
            ArrayList<Creature> creatures = habitat.getCreatures();

            StringBuilder creatureList = new StringBuilder("[");
            for (int i = 0; i < creatures.size(); i++){
                Creature c = creatures.get(i);
                creatureList.append(c.getCreatureName()).append(" - lvl ").append(c.getCreatureLevel());
                if (i != creatures.size() - 1){
                    creatureList.append(", ");
                }
            }
            creatureList.append("]");

            System.out.printf("%-17s %-15s %s\n", habitatName, level, creatureList);
        }
    }

    public void ZooUpgrade(){
        this.zooLevel++;
        maxStorage+=10;
    }

    public int getIntWoodNeeded(){
        double woodNeeded = zooLevel+zooLevel*0.5;
        int intWoodNeeded = (int) Math.round(woodNeeded);
        return intWoodNeeded;
    }

    public double getUpgradeCost(){
        double upgradeCost = zooLevel*10;
        return upgradeCost;
    }

    public boolean canUpgradeZooWood(Inventory inventory, int woodNeeded){
        if (inventory.getItems().getOrDefault("wood", 0) >= woodNeeded) {
            return true;
        }
        else{
            return false;
        }
    }

    public void upgradeZooWood(Inventory inventory){

        if (canUpgradeZooWood(inventory, getIntWoodNeeded())) {

            System.out.println("Zoo has been upgraded to level "+zooLevel+1+" using "+getIntWoodNeeded() +" wood.");
            ZooUpgrade();
        }
        else{
            System.out.println(inventory.woodCount+"/"+getIntWoodNeeded()+ ". Not enough wood. "+(getIntWoodNeeded()-inventory.woodCount)+ " more wood required.");
        }
    }

    public boolean canUpgradeZooMoney(double upgradeCost){
        if (money>=upgradeCost){
            return true;
        }
        else{
            return false;
        }
    }

    public void upgradeZooMoney(){
        if (canUpgradeZooMoney(getUpgradeCost())){
            deductMoney();
            System.out.println("Zoo has been upgraded to level "+zooLevel+1+" using "+getUpgradeCost()+" coins.");
            ZooUpgrade();
        }
        else{
            System.out.println(money+"/"+getUpgradeCost() +". Not enough coins. "+(getUpgradeCost()-money)+" more coins required to upgrade.");
        }
    }

    public void deductMoney(){
        money = money-getUpgradeCost();
    }

    public void claimProfit(Creature creature){
        double profitMoney = (creature.getDailyProfit()+50) * creature.getCreatureGoldBonus() * creature.getCreatureLevel()* creature.getDangerLevel();
        double totalMoney = profitMoney + money;
        money = totalMoney;

        System.out.println(creature.getCreatureName() + " generated "+profitMoney + " coins. ");
    }
}
