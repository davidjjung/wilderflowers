package com.davigj.wilderflowers.core.registry;


import com.davigj.wilderflowers.client.particle.FlowerPetalParticle;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.davigj.wilderflowers.core.WilderFlowers.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class WFParticleTypes {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, MOD_ID);

    public static final RegistryObject<SimpleParticleType> CHEERY_PETAL;
    public static final RegistryObject<SimpleParticleType> PLAYFUL_PETAL;
    public static final RegistryObject<SimpleParticleType> HOPEFUL_PETAL;
    public static final RegistryObject<SimpleParticleType> MOODY_PETAL;

    private static RegistryObject<SimpleParticleType> registerSimpleParticleType(boolean alwaysShow, String name) {
        return PARTICLE_TYPES.register(name, () -> {
            return new SimpleParticleType(alwaysShow);
        });
    }

    public WFParticleTypes() {
    }

    static {
        CHEERY_PETAL = registerSimpleParticleType(true, "cheery_petal");
        PLAYFUL_PETAL = registerSimpleParticleType(true, "playful_petal");
        HOPEFUL_PETAL = registerSimpleParticleType(true, "hopeful_petal");
        MOODY_PETAL = registerSimpleParticleType(true, "moody_petal");
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
    public static class RegisterParticles {
        public RegisterParticles() {
        }

        @SubscribeEvent
        public static void registerParticleTypes(RegisterParticleProvidersEvent event) {
            event.register(WFParticleTypes.CHEERY_PETAL.get(), FlowerPetalParticle.Provider::new);
            event.register(WFParticleTypes.PLAYFUL_PETAL.get(), FlowerPetalParticle.Provider::new);
            event.register(WFParticleTypes.HOPEFUL_PETAL.get(), FlowerPetalParticle.Provider::new);
            event.register(WFParticleTypes.MOODY_PETAL.get(), FlowerPetalParticle.Provider::new);
        }
    }
}
