package com.jasonjat.episodeone.registry;

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static com.jasonjat.episodeone.Episodeone.MODID;

public class ParticleRegistry {
    public static final DefaultParticleType BLUE_FLAME = FabricParticleTypes.simple();
    public static final DefaultParticleType COPPER_FLAME = FabricParticleTypes.simple();

    public static void register() {
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(MODID, "blue_flame"), BLUE_FLAME);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(MODID, "copper_flame"), COPPER_FLAME);
    }

    public static void registerClient() {
        ParticleFactoryRegistry.getInstance().register(BLUE_FLAME, FlameParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(COPPER_FLAME, FlameParticle.Factory::new);
    }
}
