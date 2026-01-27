package com.davigj.wilderflowers.core.other;

import com.davigj.wilderflowers.core.WilderFlowers;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class WFBiomeTags {
    public static final TagKey<Biome> MORE_GARLANDS = biomeTag("more_garlands");
    public static final TagKey<Biome> INFREQUENT_GARLANDS = biomeTag("infrequent_garlands");
    public static final TagKey<Biome> CHEERY_GARLANDS = biomeTag("cheery_garlands");
    public static final TagKey<Biome> MOODY_GARLANDS = biomeTag("moody_garlands");
    public static final TagKey<Biome> HOPEFUL_GARLANDS = biomeTag("hopeful_garlands");
    public static final TagKey<Biome> PLAYFUL_GARLANDS = biomeTag("playful_garlands");

    private static TagKey<Biome> biomeTag(String name) {
        return TagKey.create(Registries.BIOME, new ResourceLocation(WilderFlowers.MOD_ID, name));
    }
}
