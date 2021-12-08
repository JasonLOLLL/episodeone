package com.jasonjat.episodeone.registry;

import com.jasonjat.episodeone.entity.renderer.CopperGolemEntityRenderer;
import com.jasonjat.episodeone.entity.renderer.PurpleGolemEntityRenderer;
import com.jasonjat.episodeone.entity.renderer.SpongebobBossRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class RendererRegistry {
    public static void register() {
        EntityRendererRegistry.register(EntityRegistry.COPPER_GOLEM_ENTITY, CopperGolemEntityRenderer::new);
        EntityRendererRegistry.register(EntityRegistry.PURPLE_GOLEM_ENTITY, PurpleGolemEntityRenderer::new);
        EntityRendererRegistry.register(EntityRegistry.SPONGEBOB_BOSS_ENTITY, SpongebobBossRenderer::new);
    }
}
