package com.jasonjat.episodeone.entity.renderer;

import com.jasonjat.episodeone.entity.model.PizzaBoxEntityModel;
import com.jasonjat.episodeone.entity.projectile.PizzaBoxEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

public class PizzaBoxEntityRenderer extends GeoProjectilesRenderer<PizzaBoxEntity> {
    public PizzaBoxEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new PizzaBoxEntityModel());
    }

    @Override
    protected int getBlockLight(PizzaBoxEntity entity, BlockPos pos) {
        return 15;
    }

    @Override
    public RenderLayer getRenderType(PizzaBoxEntity animatable, float partialTicks, MatrixStack stack,
                                     VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                     Identifier textureLocation) {
        return RenderLayer.getEntityTranslucent(getTextureLocation(animatable));
    }

    @Override
    public void render(PizzaBoxEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, VertexConsumerProvider bufferIn, int packedLightIn) {

        entityYaw-=90;

        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }
}
