public class WoodlandBiome extends Biome{

    @Override
    public boolean AreBiomesCompatible(Creature creature){
        // If the creature's Biome is a Woodland it will return true, if not false (boolean)
        return creature.getCreatureBiome() instanceof WoodlandBiome;
    }
}
