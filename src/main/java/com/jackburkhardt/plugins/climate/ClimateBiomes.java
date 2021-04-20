package com.jackburkhardt.plugins.climate;

import org.bukkit.block.Biome;

public enum ClimateBiomes {

    DESERT(Biome.DESERT, Temperature.HOT),
    TUNDRA(Biome.SNOWY_TUNDRA, Temperature.COLD);

    private final Biome biome;
    private final Temperature temp;
    //private final ClimateBiomes[] allClimateBiomes;

    private ClimateBiomes(Biome biome, Temperature temp) {

        this.biome = biome;
        this.temp = temp;

    }

    public Biome getBiome() {
        return biome;
    }

    public Temperature getTemp() {
        return temp;
    }
}
