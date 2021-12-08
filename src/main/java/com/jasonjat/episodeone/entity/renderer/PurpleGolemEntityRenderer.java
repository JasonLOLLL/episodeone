package com.jasonjat.episodeone.entity.renderer;

import com.jasonjat.episodeone.entity.PurpleGolemEntity;
import com.jasonjat.episodeone.entity.model.PurpleGolemEntityModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class PurpleGolemEntityRenderer extends GeoEntityRenderer<PurpleGolemEntity> {
    public PurpleGolemEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new PurpleGolemEntityModel());
        this.shadowRadius = 0.7F;
    }
}
