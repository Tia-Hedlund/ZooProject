public class WoodlandBiome extends Biome{

    // overrides the method in biome
    @Override
    public boolean BiomesAreCompatible(Creature creature){
        // If the creature's Biome is a Woodland it will return true, if not false (boolean)
        return creature.getCreatureBiome() instanceof WoodlandBiome;
    }
}
