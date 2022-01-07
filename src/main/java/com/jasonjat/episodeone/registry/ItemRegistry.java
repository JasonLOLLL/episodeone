package com.jasonjat.episodeone.registry;

import com.jasonjat.episodeone.item.KrabbyPatty;
import com.jasonjat.episodeone.item.TestingWand;
import com.jasonjat.episodeone.item.armor.CopperArmorMaterial;
import com.jasonjat.episodeone.item.tool.CopperAxe;
import com.jasonjat.episodeone.item.tool.CopperPickaxe;
import com.jasonjat.episodeone.util.ModUtil;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.registry.Registry;

public class ItemRegistry {

    public static final ItemGroup MOD_GROUP = FabricItemGroupBuilder.build(ModUtil.id("general"), () -> new ItemStack(Items.SPONGE));

    public static final ArmorMaterial COPPER_ARMOR_MATERIAL = new CopperArmorMaterial();
    public static final Item COPPER_HELMET = new ArmorItem(COPPER_ARMOR_MATERIAL, EquipmentSlot.HEAD, new Item.Settings().group(MOD_GROUP));
    public static final Item COPPER_CHESTPLATE = new ArmorItem(COPPER_ARMOR_MATERIAL, EquipmentSlot.CHEST, new Item.Settings().group(MOD_GROUP));
    public static final Item COPPER_LEGGINGS = new ArmorItem(COPPER_ARMOR_MATERIAL, EquipmentSlot.LEGS, new Item.Settings().group(MOD_GROUP));
    public static final Item COPPER_BOOTS = new ArmorItem(COPPER_ARMOR_MATERIAL, EquipmentSlot.FEET, new Item.Settings().group(MOD_GROUP));

    public static final Item COPPER_AXE = new CopperAxe();
    public static final Item COPPER_PICKAXE = new CopperPickaxe();
    public static final Item TESTING_WAND = new TestingWand();

    public static final Item KRABBY_PATTY = new KrabbyPatty();

    public static final Item SPONGEBOB_SPAWN_EGG = new SpawnEggItem(EntityRegistry.SPONGEBOB_BOSS_ENTITY, 16776274, 0, new Item.Settings().group(MOD_GROUP));

    public static void register() {
        Registry.register(Registry.ITEM, ModUtil.id("copper_helmet"), COPPER_HELMET);
        Registry.register(Registry.ITEM, ModUtil.id("copper_chestplate"), COPPER_CHESTPLATE);
        Registry.register(Registry.ITEM, ModUtil.id("copper_leggings"), COPPER_LEGGINGS);
        Registry.register(Registry.ITEM, ModUtil.id("copper_boots"), COPPER_BOOTS);
        Registry.register(Registry.ITEM, ModUtil.id("copper_axe"), COPPER_AXE);
        Registry.register(Registry.ITEM, ModUtil.id("copper_pickaxe"), COPPER_PICKAXE);
        Registry.register(Registry.ITEM, ModUtil.id("testing_wand"), TESTING_WAND);
        Registry.register(Registry.ITEM, ModUtil.id("krabby_patty"), KRABBY_PATTY);
        Registry.register(Registry.ITEM, ModUtil.id("spongebob_spawn_egg"), SPONGEBOB_SPAWN_EGG);

    }
}
