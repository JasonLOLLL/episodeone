package com.jasonjat.episodeone.item;

import net.minecraft.item.FoodComponent;
import net.minecraft.item.FoodComponents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class KrabbyPatty extends Item {
    public KrabbyPatty() {
        super(new Item.Settings().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(8).saturationModifier(1f).meat().build()));
    }
}
