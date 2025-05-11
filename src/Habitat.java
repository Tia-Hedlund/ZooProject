import java.util.ArrayList;

public class Habitat {
    private int habitatLevel;
    public String name;
    private int creatureTotalLevelLimit;
    public ArrayList<Creature> creatures;

    public Habitat(String name, int habitatLevel, int creatureLimit){
        this.name = name;
        this.habitatLevel = habitatLevel;
        this.creatureTotalLevelLimit = creatureLimit;
    }

    public int getHabitatLevel() {
        return habitatLevel;
    }

    public int getCreatureLimit() {
        return creatureTotalLevelLimit;
    }

    // Om upgrade metoden körs så ökas level och creaturelimit med 1
    public void upgrade(){
        this.habitatLevel++;
        this.creatureTotalLevelLimit++;
    }

    // Skapa tryUpgrade() som kollar om man har tillräckligt med pengar
}
