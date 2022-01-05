package com.jasonjat.episodeone.client;

import com.jasonjat.episodeone.networking.ModPacketsClient;
import com.jasonjat.episodeone.registry.ParticleRegistry;
import com.jasonjat.episodeone.registry.RendererRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class EpisodeoneClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ParticleRegistry.registerClient();
        RendererRegistry.register();
        ModPacketsClient.register();
    }
}
