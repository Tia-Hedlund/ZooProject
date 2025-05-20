import java.sql.Array;
import java.util.ArrayList;

public class Habitat extends Buyable{
    private int habitatLevel;
    private String habitatName;
    private int creatureTotalLevelLimit;
    private int totalLevelInHabitat;
    private ArrayList<Creature> creatures;
    public Biome habitatBiome;

    public Habitat(double price, String habitatName, int habitatLevel, int creatureLevelLimit, Biome habitatBiome, int totalLevelInHabitat){
        super(price);
        this.habitatName = habitatName;
        this.habitatLevel = habitatLevel;
        this.creatureTotalLevelLimit = creatureLevelLimit;
        this.habitatBiome = habitatBiome;
        creatures = new ArrayList<>();
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

    public int getCreatureLimit() {
        return creatureTotalLevelLimit;
    }

    public String getHabitatName() {
        return habitatName;
    }

    // Om upgrade metoden körs så ökas level och creaturelimit med 1
    public void upgrade(){
        this.habitatLevel++;
        this.creatureTotalLevelLimit++;
    }


    // Skapa tryUpgrade() som kollar om man har tillräckligt med pengar (skapa i shoppen)
}
