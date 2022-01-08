package com.jasonjat.episodeone.item.armor;

import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class SpongeArmorRenderer extends GeoArmorRenderer<SpongeArmorItem> {
    public SpongeArmorRenderer() {
        super(new SpongeArmorModel());

        this.headBone = "armorHead";
        this.bodyBone = "armorBody";
        this.rightArmBone = "armorRightArm";
        this.leftArmBone = "armorLeftArm";
        // leg bones are switched :/
        this.rightLegBone = "armorLeftLeg";
        this.leftLegBone = "armorRightLeg";
        this.rightBootBone = "armorLeftBoot";
        this.leftBootBone = "armorRightBoot";
    }
}
