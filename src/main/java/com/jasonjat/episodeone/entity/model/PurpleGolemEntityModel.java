package com.jasonjat.episodeone.entity.model;

import com.jasonjat.episodeone.entity.PurpleGolemEntity;
import com.jasonjat.episodeone.util.ModUtil;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class PurpleGolemEntityModel extends AnimatedTickingGeoModel<PurpleGolemEntity> {
    @Override
    public Identifier getModelLocation(PurpleGolemEntity object) {
        return ModUtil.id("geo/purple_golem.geo.json");
    }

    @Override
    public Identifier getTextureLocation(PurpleGolemEntity object) {
        return ModUtil.id("textures/entity/purple_golem.png");
    }

    @Override
    public Identifier getAnimationFileLocation(PurpleGolemEntity animatable) {
        return ModUtil.id("animations/purple_golem.animation.json");
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setLivingAnimations(PurpleGolemEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
        }
    }
}
