package com.davigj.wilderflowers.core.compat;

import com.davigj.wilderflowers.core.WilderFlowers;
import com.davigj.wilderflowers.core.registry.WFBlocks;
import net.mehvahdjukaar.supplementaries.common.utils.FlowerPotHandler;

public class SupplementariesCompat {

    public static void register() {
        FlowerPotHandler.registerCustomFlower(WFBlocks.CHEERFUL_WILDFLOWERS.getItemSupplier().get(), WilderFlowers.locate("block/cheery_wildflowers_potted"));
        FlowerPotHandler.registerCustomFlower(WFBlocks.PINK_PETALS.getItemSupplier().get(), WilderFlowers.locate("block/playful_wildflowers_potted"));
        FlowerPotHandler.registerCustomFlower(WFBlocks.MOODY_WILDFLOWERS.getItemSupplier().get(), WilderFlowers.locate("block/moody_wildflowers_potted"));
        FlowerPotHandler.registerCustomFlower(WFBlocks.HOPEFUL_WILDFLOWERS.getItemSupplier().get(), WilderFlowers.locate("block/hopeful_wildflowers_potted"));
        FlowerPotHandler.registerCustomFlower(WFBlocks.CLOVERS.getItemSupplier().get(), WilderFlowers.locate("block/clovers_potted"));
    }
}
