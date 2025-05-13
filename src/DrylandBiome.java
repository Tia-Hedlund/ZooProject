public abstract class DrylandBiome extends Biome{

    @Override
    public boolean BiomesAreCompatible(Creature creature){
        // If the creature's Biome is a DrylandBiome it will return true, if not false (boolean)
        return creature.getCreatureBiome() instanceof DrylandBiome;
    }

}
