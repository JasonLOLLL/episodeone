package com.jasonjat.episodeone.entity.renderer;

import com.jasonjat.episodeone.entity.CopperGolemEntity;
import com.jasonjat.episodeone.entity.model.CopperGolemEntityModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class CopperGolemEntityRenderer extends GeoEntityRenderer<CopperGolemEntity> {
    public CopperGolemEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new CopperGolemEntityModel());
        this.shadowRadius = 0.25F;
    }
}
