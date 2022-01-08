package com.jasonjat.episodeone.item;

import com.jasonjat.episodeone.registry.ItemRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;

public class KrabbyPatty extends Item {
    public KrabbyPatty() {
        super(new Item.Settings().group(ItemRegistry.MOD_GROUP).food(new FoodComponent.Builder().
                hunger(8).
                saturationModifier(1f).
                meat().
                alwaysEdible().
                statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 1200, 1), 1).
                statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 1200, 10), 1)
                .build()));
    }
}
