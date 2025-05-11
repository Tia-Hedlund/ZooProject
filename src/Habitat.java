import java.util.ArrayList;

public class Habitat {
    private int level;
    public String name;
    private int creatureTotalLevelLimit;
    public ArrayList<Creature> creatures;

    public Habitat(String name, int level, int creatureLimit){
        this.name = name;
        this.level = level;
        this.creatureTotalLevelLimit = creatureLimit;
    }

    public int getLevel() {
        return level;
    }

    public int getCreatureLimit() {
        return creatureTotalLevelLimit;
    }

    // Om upgrade metoden körs så ökas level och creaturelimit med 1
    public void upgrade(){
        this.level++;
        this.creatureTotalLevelLimit++;
    }

    // Skapa tryUpgrade() som kollar om man har tillräckligt med pengar
}
