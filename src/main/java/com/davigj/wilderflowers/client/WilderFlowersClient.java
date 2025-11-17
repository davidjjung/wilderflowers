package com.davigj.wilderflowers.client;

import com.davigj.wilderflowers.client.event.FlowerGarlandEvent;
import com.davigj.wilderflowers.client.registry.WFItemProperties;
import com.davigj.wilderflowers.core.WilderFlowers;
import com.davigj.wilderflowers.core.registry.BlockSupplier;
import com.davigj.wilderflowers.core.registry.WFBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod.EventBusSubscriber(modid = WilderFlowers.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class WilderFlowersClient {

    public static void init(FMLJavaModLoadingContext context) {
        MinecraftForge.EVENT_BUS.addListener(WilderFlowersClient::clickTick);
    }

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event){
        BlockColors blockColors = event.getBlockColors();
        for (BlockSupplier block : WFBlocks.FOLIAGE_BLOCKS) {
            event.register(((state, view, pos, tintIndex) -> {
                if (view == null || pos == null) {
                    return 9551193;
                }
                return BiomeColors.getAverageFoliageColor(view, pos);
            }), block.getBlock());
        }
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        WFItemProperties.register();
    }

    // Forge event bus.
    public static void clickTick(TickEvent.ClientTickEvent event) {
        FlowerGarlandEvent.tick(Minecraft.getInstance());
    }

    @SubscribeEvent
    public static void onModelBake(ModelEvent.RegisterAdditional event) {
        // This should probably be extracted into a handler of some kind
        event.register(WilderFlowers.locate("block/cheery_wildflowers_potted"));
        event.register(WilderFlowers.locate("block/moody_wildflowers_potted"));
        event.register(WilderFlowers.locate("block/playful_wildflowers_potted"));
        event.register(WilderFlowers.locate("block/hopeful_wildflowers_potted"));
        event.register(WilderFlowers.locate("block/clovers_potted"));
    }
}
