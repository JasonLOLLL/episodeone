package com.jasonjat.episodeone.entity.model;

import com.jasonjat.episodeone.entity.PurpleGolemEntity;
import com.jasonjat.episodeone.entity.SpongebobBossEntity;
import com.jasonjat.episodeone.util.ModUtil;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class SpongebobBossModel extends AnimatedTickingGeoModel<SpongebobBossEntity> {
    @Override
    public Identifier getModelLocation(SpongebobBossEntity object) {
        return ModUtil.id("geo/spongebob_boss.geo.json");
    }

    @Override
    public Identifier getTextureLocation(SpongebobBossEntity object) {
        return ModUtil.id("textures/entity/spongebob_boss.png");
    }

    @Override
    public Identifier getAnimationFileLocation(SpongebobBossEntity animatable) {
        return ModUtil.id("animations/spongebob_boss.animation.json");
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setLivingAnimations(SpongebobBossEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
        }
    }
}
