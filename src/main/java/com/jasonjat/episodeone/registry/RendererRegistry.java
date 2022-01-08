package com.jasonjat.episodeone.registry;

import com.jasonjat.episodeone.entity.renderer.*;
import com.jasonjat.episodeone.item.armor.SpongeArmorItem;
import com.jasonjat.episodeone.item.armor.SpongeArmorRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class RendererRegistry {

    public static void register() {
        EntityRendererRegistry.register(EntityRegistry.COPPER_GOLEM_ENTITY, CopperGolemEntityRenderer::new);
        EntityRendererRegistry.register(EntityRegistry.PURPLE_GOLEM_ENTITY, PurpleGolemEntityRenderer::new);
        EntityRendererRegistry.register(EntityRegistry.SPONGEBOB_BOSS_ENTITY, SpongebobBossRenderer::new);
        EntityRendererRegistry.register(EntityRegistry.CIRCLE_BLAST_ENTITY, CircleBlastEntityRenderer::new);
        EntityRendererRegistry.register(EntityRegistry.PIZZA_BOX_ENTITY, PizzaBoxEntityRenderer::new);
        EntityRendererRegistry.register(EntityRegistry.DOODLEBOB_ENTITY, DoodlebobEntityRenderer::new);
        GeoArmorRenderer.registerArmorRenderer(new SpongeArmorRenderer(), ItemRegistry.SPONGE_HELMET, ItemRegistry.SPONGE_CHESTPLATE, ItemRegistry.SPONGE_LEGGINGS, ItemRegistry.SPONGE_BOOTS);
    }
}
