package com.davigj.wilderflowers.core;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class WFConfig {
    public static class Common {
        public final ForgeConfigSpec.ConfigValue<Double> garlandChance;
        public final ForgeConfigSpec.ConfigValue<Double> highGarlandChance;
        public final ForgeConfigSpec.ConfigValue<Boolean> garlandWearersNeutral;
        public final ForgeConfigSpec.ConfigValue<Boolean> garlandWearersPassive;

        Common (ForgeConfigSpec.Builder builder) {
            builder.push("wilderflowers");
            builder.push("garland_spawns");
            garlandChance = builder.comment("Chance of mobs in garland_wearers spawning with flower garlands").define("Base garland chance", 0.05);
            highGarlandChance = builder.comment("Chance of mobs in garland_wearers spawning with flower garlands in frequent_garland biomes").define("Frequent garland chance", 0.35);
            builder.pop();
            builder.push("mob_flower_garland_behavior");
            garlandWearersNeutral = builder.comment("Mobs in garland_wearers wearing flower garlands are neutral").define("Garland wearers neutral", false);
            garlandWearersPassive = builder.comment("Mobs in garland_wearers wearing flower garlands are passive. If true, overrides garlandWearersNeutral").define("Garland wearers passive", false);
            builder.pop();
            builder.pop();
        }
    }

    static final ForgeConfigSpec COMMON_SPEC;
    public static final WFConfig.Common COMMON;


    static {
        final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(WFConfig.Common::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();
    }
}
