package com.davigj.wilderflowers.core;

import com.davigj.wilderflowers.client.WilderFlowersClient;
import com.davigj.wilderflowers.core.compat.SupplementariesCompat;
import com.davigj.wilderflowers.core.registry.*;
import net.minecraft.SharedConstants;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.resource.PathPackResources;
import oshi.util.tuples.Pair;

import java.nio.file.Path;

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
        bus.addListener(this::addAdvancementOverrides);

        if (FMLEnvironment.dist.isClient()) {
            WilderFlowersClient.init(FMLJavaModLoadingContext.get());
        }
        bus.addListener(WFCreativePlacements::set);
        context.registerConfig(ModConfig.Type.COMMON, WFConfig.COMMON_SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        for (Pair<RegistryObject<Block>, RegistryObject<BlockItem>> foliageBlock : FOLIAGE_BLOCKS) {
            ComposterBlock.COMPOSTABLES.put(foliageBlock.getB().get(), 0.3f);
        }
        event.enqueueWork(WFBlocks::addPottedPlants);

        if (ModList.get().isLoaded("supplementaries")) {
            SupplementariesCompat.register();
        }
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

    private void addAdvancementOverrides(AddPackFindersEvent event) {
        if (event.getPackType() == PackType.CLIENT_RESOURCES) {
            Path resourcePath = ModList.get().getModFileById(MOD_ID).getFile().findResource("resourcepacks/pink_petal_overrides");
            PathPackResources pack = new PathPackResources(ModList.get().getModFileById(MOD_ID).getFile().getFileName() + ":" + resourcePath, true, resourcePath);
            PackMetadataSection metadata = new PackMetadataSection(Component.empty().append("Overrides the name and textures of pink petals"), SharedConstants.getCurrentVersion().getPackVersion(PackType.CLIENT_RESOURCES));
            event.addRepositorySource((source) ->
                    source.accept(Pack.create(
                            "builtin/pink_petal_overrides",
                            Component.empty().append("Pink Petal Overrides"),
                            false,
                            (string) -> pack,
                            new Pack.Info(metadata.getDescription(), metadata.getPackFormat(PackType.SERVER_DATA), metadata.getPackFormat(PackType.CLIENT_RESOURCES), FeatureFlagSet.of(), pack.isHidden()),
                            PackType.CLIENT_RESOURCES,
                            Pack.Position.TOP,
                            false,
                            PackSource.BUILT_IN)
                    )
            );
        }
    }
}