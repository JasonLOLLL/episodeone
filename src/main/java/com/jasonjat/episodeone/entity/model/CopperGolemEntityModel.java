package com.jasonjat.episodeone.entity.model;

import com.jasonjat.episodeone.entity.CopperGolemEntity;
import com.jasonjat.episodeone.util.ModUtil;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CopperGolemEntityModel extends AnimatedGeoModel<CopperGolemEntity> {
    @Override
    public Identifier getModelLocation(CopperGolemEntity object) {
        return ModUtil.id("geo/copper_golem.geo.json");
    }

    @Override
    public Identifier getTextureLocation(CopperGolemEntity object) {
        return ModUtil.id("textures/entity/copper_golem.png");
    }

    @Override
    public Identifier getAnimationFileLocation(CopperGolemEntity animatable) {
        return ModUtil.id("animations/copper_golem.animation.json");
    }
}
