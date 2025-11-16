package com.davigj.wilderflowers.client.registry;

import com.davigj.wilderflowers.core.WilderFlowers;
import com.davigj.wilderflowers.core.registry.WFItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.renderer.item.ItemPropertyFunction;
import net.minecraft.world.entity.EquipmentSlot;

public class WFItemProperties {

    private static final ItemPropertyFunction FLOWER_GARLAND_EQUIPPED = (stack, level, entity, i) -> {
        if (entity == null) {
            return 0.0F;
        } else {
            return entity.getItemBySlot(EquipmentSlot.HEAD) == stack ? 1.0F : 0.0F;
        }
    };

    public static void register() {
        ItemProperties.register(WFItems.CHEERY_WILDFLOWER_GARLAND.get(), WilderFlowers.locate("equipped"), FLOWER_GARLAND_EQUIPPED);
        ItemProperties.register(WFItems.HOPEFUL_WILDFLOWER_GARLAND.get(), WilderFlowers.locate("equipped"), FLOWER_GARLAND_EQUIPPED);
        ItemProperties.register(WFItems.PLAYFUL_WILDFLOWER_GARLAND.get(), WilderFlowers.locate("equipped"), FLOWER_GARLAND_EQUIPPED);
        ItemProperties.register(WFItems.MOODY_WILDFLOWER_GARLAND.get(), WilderFlowers.locate( "equipped"), FLOWER_GARLAND_EQUIPPED);
    }
}