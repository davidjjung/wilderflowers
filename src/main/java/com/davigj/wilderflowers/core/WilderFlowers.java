package com.davigj.wilderflowers.core;

import com.davigj.wilderflowers.client.WilderFlowersClient;
import com.davigj.wilderflowers.client.event.FlowerGarlandEvent;
import com.davigj.wilderflowers.core.registry.BlockSupplier;
import com.davigj.wilderflowers.core.registry.WFBlocks;
import com.davigj.wilderflowers.core.registry.WFItems;
import com.davigj.wilderflowers.core.registry.WFParticleTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;

import static com.davigj.wilderflowers.core.registry.WFBlocks.FOLIAGE_BLOCKS;

@Mod(WilderFlowers.MOD_ID)
public class WilderFlowers {
    public static final String MOD_ID = "wilderflowers";

    public WilderFlowers() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext context = ModLoadingContext.get();
        MinecraftForge.EVENT_BUS.register(this);
        WFBlocks.register(bus);
        WFItems.ITEMS.register(bus);
        WFParticleTypes.PARTICLE_TYPES.register(bus);

        bus.addListener(this::commonSetup);
        bus.addListener(this::clientSetup);
        bus.addListener(this::dataSetup);
        if (FMLEnvironment.dist.isClient()) {
            WilderFlowersClient.init(FMLJavaModLoadingContext.get());
        }

        context.registerConfig(ModConfig.Type.COMMON, TemplateConfig.COMMON_SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        for (BlockSupplier foliageBlock : FOLIAGE_BLOCKS) {
            ComposterBlock.COMPOSTABLES.put(foliageBlock.getBlockSupplier().get(), 0.3f);
        }
        event.enqueueWork(WFBlocks::addPottedPlants);
    }

    private void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {

        });
    }

    private void dataSetup(GatherDataEvent event) {

    }

    public static ResourceLocation locate(String id) {
        return identifier(MOD_ID, id);
    }

    public static ResourceLocation identifier(String namespace, String id) {
        return new ResourceLocation(namespace, id);
    }
}