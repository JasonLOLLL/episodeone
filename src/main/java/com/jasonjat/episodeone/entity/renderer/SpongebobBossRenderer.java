package com.jasonjat.episodeone.entity.renderer;

import com.jasonjat.episodeone.entity.SpongebobBossEntity;
import com.jasonjat.episodeone.entity.model.SpongebobBossModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.math.BlockPos;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class SpongebobBossRenderer extends GeoEntityRenderer<SpongebobBossEntity> {
    public SpongebobBossRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new SpongebobBossModel());
        this.shadowRadius = 1F;
    }

    @Override
    protected int getBlockLight(SpongebobBossEntity entity, BlockPos pos) {
        return 15;
    }
}
