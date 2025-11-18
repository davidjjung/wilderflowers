package com.davigj.wilderflowers.core.registry;

import com.davigj.wilderflowers.common.item.FlowerGarlandItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static com.davigj.wilderflowers.core.WilderFlowers.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WFItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    public static Supplier<Item> CHEERY_WILDFLOWER_GARLAND = registerItem("cheery_wildflower_garland", () -> new FlowerGarlandItem(WFBlocks.CHEERY_WILDFLOWER_GARLAND.get(), new Item.Properties()));
    public static Supplier<Item> HOPEFUL_WILDFLOWER_GARLAND = registerItem("hopeful_wildflower_garland", () -> new FlowerGarlandItem(WFBlocks.HOPEFUL_WILDFLOWER_GARLAND.get(), new Item.Properties()));
    public static Supplier<Item> PLAYFUL_WILDFLOWER_GARLAND = registerItem("playful_wildflower_garland", () -> new FlowerGarlandItem(WFBlocks.PLAYFUL_WILDFLOWER_GARLAND.get(), new Item.Properties()));
    public static Supplier<Item> MOODY_WILDFLOWER_GARLAND = registerItem("moody_wildflower_garland", () -> new FlowerGarlandItem(WFBlocks.MOODY_WILDFLOWER_GARLAND.get(), new Item.Properties()));

    public static RegistryObject<Item> registerItem(String itemID, Supplier<Item> item) {
        return ITEMS.register(itemID, item);
    }
}
