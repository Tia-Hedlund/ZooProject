import java.sql.Array;
import java.util.ArrayList;

public class Habitat {
    private int habitatLevel;
    private String habitatName;
    private int creatureTotalLevelLimit;
    public ArrayList<Creature> creatures;

    public Habitat(String name, int habitatLevel, int creatureLimit){
        this.habitatName = name;
        this.habitatLevel = habitatLevel;
        this.creatureTotalLevelLimit = creatureLimit;
        creatures = new ArrayList<>();
    }

    // kanske måste flytta sen så addCreature är olika för de olika habitats
    public void addCreature(Creature creature){
        creatures.add(creature);
    }

    public int getHabitatLevel() {
        return habitatLevel;
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


    // Skapa tryUpgrade() som kollar om man har tillräckligt med pengar
}
