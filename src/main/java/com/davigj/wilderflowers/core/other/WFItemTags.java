package com.davigj.wilderflowers.core.other;

import com.davigj.wilderflowers.core.WilderFlowers;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class WFItemTags {
    public static final TagKey<Item> FLOWER_GARLANDS = biomeTag("flower_garlands");

    private static TagKey<Item> biomeTag(String name) {
        return TagKey.create(Registries.ITEM, new ResourceLocation(WilderFlowers.MOD_ID, name));
    }
}
