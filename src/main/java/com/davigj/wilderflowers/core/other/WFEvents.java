package com.davigj.wilderflowers.core.other;

import com.davigj.wilderflowers.core.WFConfig;
import com.davigj.wilderflowers.core.WilderFlowers;
import com.davigj.wilderflowers.core.registry.WFItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

import static com.davigj.wilderflowers.core.other.WFEntityTypeTags.GARLAND_WEARERS;
import static com.davigj.wilderflowers.core.other.WFItemTags.FLOWER_GARLANDS;

@Mod.EventBusSubscriber(modid = WilderFlowers.MOD_ID)
public class WFEvents {
    @SubscribeEvent
    public static void onEntityJoinWorld(EntityJoinLevelEvent event) {
        Entity entity = event.getEntity();

        if (!(entity instanceof Mob mob)) return;
        if (!entity.getType().is(WFEntityTypeTags.GARLAND_WEARERS)) return;

        BlockPos pos = entity.blockPosition();
        var level = entity.level();
        var random = mob.getRandom();
        double roll = random.nextDouble();

        Holder<Biome> biome = level.getBiome(pos);

        boolean frequent = roll < WFConfig.COMMON.highGarlandChance.get() && biome.is(WFBiomeTags.MORE_GARLANDS);
        boolean infrequent = roll < WFConfig.COMMON.garlandChance.get() && biome.is(WFBiomeTags.INFREQUENT_GARLANDS);

        if (!frequent && !infrequent) return;

        List<ItemStack> roulette = new ArrayList<>();

        if (biome.is(WFBiomeTags.CHEERY_GARLANDS)) {
            roulette.add(WFItems.CHEERY_WILDFLOWER_GARLAND.get().getDefaultInstance());
        }
        if (biome.is(WFBiomeTags.MOODY_GARLANDS)) {
            roulette.add(WFItems.MOODY_WILDFLOWER_GARLAND.get().getDefaultInstance());
        }
        if (biome.is(WFBiomeTags.PLAYFUL_GARLANDS)) {
            roulette.add(WFItems.PLAYFUL_WILDFLOWER_GARLAND.get().getDefaultInstance());
        }
        if (biome.is(WFBiomeTags.HOPEFUL_GARLANDS)) {
            roulette.add(WFItems.HOPEFUL_WILDFLOWER_GARLAND.get().getDefaultInstance());
        }

        if (roulette.isEmpty()) return;

        ItemStack stack = roulette.get(random.nextInt(roulette.size()));
        mob.setItemSlot(EquipmentSlot.HEAD, stack);
    }


    @SubscribeEvent
    public static void onChangeTarget(LivingChangeTargetEvent event) {
        LivingEntity living = event.getEntity();
        if (living.level().isClientSide) return;
        if (!(living instanceof Mob mob)) return;

        if (!mob.getType().is(GARLAND_WEARERS) || !mob.getItemBySlot(EquipmentSlot.HEAD).is(FLOWER_GARLANDS)) return;

        if (WFConfig.COMMON.garlandWearersPassive.get()) {
            event.setNewTarget(null);
            return;
        }

        if (!WFConfig.COMMON.garlandWearersNeutral.get()) return;

        LivingEntity target = event.getNewTarget();
        if (target == null) return;

        if (target == mob.getLastAttacker()) return; // retribution is a must

        if (target instanceof ServerPlayer) {
            event.setNewTarget(null);
        }
    }

}