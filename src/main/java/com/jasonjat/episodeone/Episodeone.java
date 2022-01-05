package com.jasonjat.episodeone;

import com.jasonjat.episodeone.registry.EntityRegistry;
import com.jasonjat.episodeone.registry.ItemRegistry;
import com.jasonjat.episodeone.registry.ParticleRegistry;
import net.fabricmc.api.ModInitializer;
import software.bernie.geckolib3.GeckoLib;

public class Episodeone implements ModInitializer {

    public static final String MODID = "eo";

    @Override
    public void onInitialize() {
        GeckoLib.initialize();
//        GeckoLibMod.DISABLE_IN_DEV = true;
        ItemRegistry.register();
        ParticleRegistry.register();
        EntityRegistry.register();
    }
}
