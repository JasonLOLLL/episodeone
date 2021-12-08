package com.jasonjat.episodeone.entity.model;

import com.jasonjat.episodeone.entity.SpongebobBossEntity;
import com.jasonjat.episodeone.util.ModUtil;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SpongebobBossModel extends AnimatedGeoModel<SpongebobBossEntity> {
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
}
