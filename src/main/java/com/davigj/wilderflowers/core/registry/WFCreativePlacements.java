package com.davigj.wilderflowers.core.registry;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;

public class WFCreativePlacements {
    public static void set(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey().equals(CreativeModeTabs.NATURAL_BLOCKS)) {
            event.accept(WFBlocks.getItem(WFBlocks.CLOVERS));
            event.accept(WFBlocks.getItem(WFBlocks.CHEERFUL_WILDFLOWERS));
            event.accept(WFBlocks.getItem(WFBlocks.MOODY_WILDFLOWERS));
            event.accept(WFBlocks.getItem(WFBlocks.HOPEFUL_WILDFLOWERS));
        } else if (event.getTabKey().equals(CreativeModeTabs.BUILDING_BLOCKS)) {
            event.accept(WFItems.CHEERY_WILDFLOWER_GARLAND);
            event.accept(WFItems.MOODY_WILDFLOWER_GARLAND);
            event.accept(WFItems.PLAYFUL_WILDFLOWER_GARLAND);
            event.accept(WFItems.HOPEFUL_WILDFLOWER_GARLAND);
        }
    }
}
