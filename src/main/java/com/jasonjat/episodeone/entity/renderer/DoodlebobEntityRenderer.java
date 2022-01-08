package com.jasonjat.episodeone.entity.renderer;

import com.jasonjat.episodeone.entity.DoodlebobEntity;
import com.jasonjat.episodeone.entity.model.DoodlebobEntityModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class DoodlebobEntityRenderer extends GeoEntityRenderer<DoodlebobEntity> {
    public DoodlebobEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new DoodlebobEntityModel());
    }
}
