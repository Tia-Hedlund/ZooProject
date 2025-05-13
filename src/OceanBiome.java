public class OceanBiome extends Biome{

    @Override
    public boolean AreBiomesCompatible(Creature creature){
        // If the creature's Biome is a OceanBiome it will return true, if not false (boolean)
        return creature.getCreatureBiome() instanceof OceanBiome;
    }
}
