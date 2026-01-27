package com.davigj.wilderflowers.core.other;

import com.davigj.wilderflowers.core.WilderFlowers;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public class WFEntityTypeTags {
    public static final TagKey<EntityType<?>> GARLAND_WEARERS = entityTypeTag("garland_wearers");

    private static TagKey<EntityType<?>> entityTypeTag(String name) {
        return TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation(WilderFlowers.MOD_ID, name));
    }
}
