package com.jasonjat.episodeone.item;

import com.jasonjat.episodeone.registry.ItemRegistry;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.FoodComponents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class KrabbyPatty extends Item {
    public KrabbyPatty() {
        super(new Item.Settings().group(ItemRegistry.MOD_GROUP).food(new FoodComponent.Builder().hunger(8).saturationModifier(1f).meat().build()));
    }
}
