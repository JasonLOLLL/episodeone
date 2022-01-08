package com.jasonjat.episodeone.entity.model;

import com.jasonjat.episodeone.entity.DoodlebobEntity;
import com.jasonjat.episodeone.util.ModUtil;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DoodlebobEntityModel extends AnimatedGeoModel<DoodlebobEntity> {
    @Override
    public Identifier getModelLocation(DoodlebobEntity object) {
        return ModUtil.id("geo/doodlebob.geo.json");
    }

    @Override
    public Identifier getTextureLocation(DoodlebobEntity object) {
        return ModUtil.id("textures/entity/doodlebob.png");
    }

    @Override
    public Identifier getAnimationFileLocation(DoodlebobEntity animatable) {
        return ModUtil.id("animations/doodlebob.animation.json");
    }
}
