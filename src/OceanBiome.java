public abstract class OceanBiome extends Biome{

    @Override
    public boolean BiomesAreCompatible(Creature creature){
        // If the creature's Biome is a OceanBiome it will return true, if not false (boolean)
        return creature.getCreatureBiome() instanceof OceanBiome;
    }
}
