package com.davigj.wilderflowers.core.compat;

import com.davigj.wilderflowers.core.WilderFlowers;
import com.davigj.wilderflowers.core.registry.WFBlocks;
import net.mehvahdjukaar.supplementaries.common.utils.FlowerPotHandler;
import net.minecraft.world.item.Items;

public class SupplementariesCompat {

    public static void register() {
        FlowerPotHandler.registerCustomFlower(WFBlocks.CHEERFUL_WILDFLOWERS.getB().get(), WilderFlowers.locate("block/cheery_wildflowers_potted"));
        FlowerPotHandler.registerCustomFlower(WFBlocks.MOODY_WILDFLOWERS.getB().get(), WilderFlowers.locate("block/moody_wildflowers_potted"));
        FlowerPotHandler.registerCustomFlower(WFBlocks.HOPEFUL_WILDFLOWERS.getB().get(), WilderFlowers.locate("block/hopeful_wildflowers_potted"));
        FlowerPotHandler.registerCustomFlower(WFBlocks.CLOVERS.getB().get(), WilderFlowers.locate("block/clovers_potted"));
    }
}
