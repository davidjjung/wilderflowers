package com.davigj.wilderflowers.core.registry;

import com.davigj.wilderflowers.common.block.FlowerBedBlock;
import com.davigj.wilderflowers.common.block.FlowerGarlandBlock;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import oshi.util.tuples.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

import static com.davigj.wilderflowers.core.WilderFlowers.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WFBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
    public static final Map<ResourceLocation, RegistryObject<Block>> POTTED_PLANTS = new HashMap<>();

    public static Pair<RegistryObject<Block>, RegistryObject<BlockItem>>
            CHEERFUL_WILDFLOWERS = registerBlock("cheery_wildflowers",
            ()-> new FlowerBedBlock(flowerBedProperties()));

    public static Pair<RegistryObject<Block>, RegistryObject<BlockItem>>
            PINK_PETALS = registerBlock("pink_petals",
            ()-> new FlowerBedBlock(flowerBedProperties()));

    public static Pair<RegistryObject<Block>, RegistryObject<BlockItem>>
            CLOVERS = registerBlock("clovers",
            ()-> new FlowerBedBlock(flowerBedProperties().replaceable()));

    public static Pair<RegistryObject<Block>, RegistryObject<BlockItem>>
            MOODY_WILDFLOWERS = registerBlock("moody_wildflowers",
            ()-> new FlowerBedBlock(flowerBedProperties()));

    public static Pair<RegistryObject<Block>, RegistryObject<BlockItem>>
            HOPEFUL_WILDFLOWERS = registerBlock("hopeful_wildflowers",
            ()-> new FlowerBedBlock(flowerBedProperties()));

    public static final RegistryObject<Block> POTTED_CHEERY_WILDFLOWERS = registerPottedPlant(CHEERFUL_WILDFLOWERS);
    public static final RegistryObject<Block> POTTED_HOPEFUL_WILDFLOWERS = registerPottedPlant(HOPEFUL_WILDFLOWERS);
    public static final RegistryObject<Block> POTTED_PLAYFUL_WILDFLOWERS = BLOCKS.register("potted_pink_petals",
            () -> new FlowerPotBlock(Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("minecraft", "pink_petals"))),
                            BlockBehaviour.Properties.copy(Blocks.POTTED_PINK_TULIP)));
    public static final RegistryObject<Block> POTTED_MOODY_WILDFLOWERS = registerPottedPlant(MOODY_WILDFLOWERS);
    public static final RegistryObject<Block> POTTED_CLOVERS = registerPottedPlant(CLOVERS);

    public static  RegistryObject<Block>
            CHEERY_WILDFLOWER_GARLAND = WFBlocks.BLOCKS.register("cheery_wildflower_garland", ()-> new FlowerGarlandBlock(flowerBedProperties()));

    public static  RegistryObject<Block>
            HOPEFUL_WILDFLOWER_GARLAND = WFBlocks.BLOCKS.register("hopeful_wildflower_garland", ()-> new FlowerGarlandBlock(flowerBedProperties()));

    public static  RegistryObject<Block>
            PLAYFUL_WILDFLOWER_GARLAND = WFBlocks.BLOCKS.register("playful_wildflower_garland", ()-> new FlowerGarlandBlock(flowerBedProperties()));

    public static  RegistryObject<Block>
            MOODY_WILDFLOWER_GARLAND = WFBlocks.BLOCKS.register("moody_wildflower_garland", ()-> new FlowerGarlandBlock(flowerBedProperties()));


    public static void addPottedPlants() {
        POTTED_PLANTS.forEach(((FlowerPotBlock) Blocks.FLOWER_POT)::addPlant);
    }

    public static final ArrayList<Pair<RegistryObject<Block>, RegistryObject<BlockItem>>> FOLIAGE_BLOCKS = new ArrayList<Pair<RegistryObject<Block>, RegistryObject<BlockItem>>>();

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        FOLIAGE_BLOCKS.add(CHEERFUL_WILDFLOWERS);
        FOLIAGE_BLOCKS.add(MOODY_WILDFLOWERS);
        FOLIAGE_BLOCKS.add(HOPEFUL_WILDFLOWERS);
    }

    public static RegistryObject<Block> registerPottedPlant(Pair<RegistryObject<Block>, RegistryObject<BlockItem>> block) {
        RegistryObject<Block> pottedBlock = BLOCKS.register("potted_" + block.getA().getId().getPath(), () -> new FlowerPotBlock(
                block.getA().get(),
                BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)
        ));
        POTTED_PLANTS.put(block.getB().getId(), pottedBlock);
        return pottedBlock;
    }

    private static BlockBehaviour.Properties flowerBedProperties() {
        return BlockBehaviour.Properties.of().noCollission().sound(SoundType.FLOWERING_AZALEA);
    }

    public static Pair<RegistryObject<Block>, RegistryObject<BlockItem>> registerBlock(String blockID, Supplier<Block> blockSupplier) {
        final var block = BLOCKS.register(blockID, blockSupplier);
        final var item = WFItems.ITEMS.register(blockID, () -> new BlockItem(block.get(), new Item.Properties()));
        return new Pair<>(block, item);
    }

    public static Item getItem(Pair<RegistryObject<Block>, RegistryObject<BlockItem>> block) {
        return block.getB().get();
    }

}