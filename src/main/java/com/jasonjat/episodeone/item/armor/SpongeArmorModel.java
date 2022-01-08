package com.jasonjat.episodeone.item.armor;

import com.jasonjat.episodeone.util.ModUtil;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SpongeArmorModel extends AnimatedGeoModel<SpongeArmorItem> {
    @Override
    public Identifier getModelLocation(SpongeArmorItem object) {
        return ModUtil.id("geo/sponge_armor.geo.json");
    }

    @Override
    public Identifier getTextureLocation(SpongeArmorItem object) {
        return ModUtil.id("textures/models/sponge_armor.png");
    }

    @Override
    public Identifier getAnimationFileLocation(SpongeArmorItem animatable) {
        return null;
    }
}
