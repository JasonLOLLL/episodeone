package com.jasonjat.episodeone.entity.model;

import com.jasonjat.episodeone.entity.projectile.PizzaBoxEntity;
import com.jasonjat.episodeone.util.ModUtil;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class PizzaBoxEntityModel extends AnimatedGeoModel<PizzaBoxEntity> {
    @Override
    public Identifier getModelLocation(PizzaBoxEntity object) {
        return ModUtil.id("geo/pizza_box.geo.json");
    }

    @Override
    public Identifier getTextureLocation(PizzaBoxEntity object) {
        return ModUtil.id("textures/entity/pizza_box.png");
    }

    @Override
    public Identifier getAnimationFileLocation(PizzaBoxEntity animatable) {
        return ModUtil.id("animations/pizza_box.animation.json");
    }
}
