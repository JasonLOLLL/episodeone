package com.jasonjat.episodeone.registry;

import com.jasonjat.episodeone.item.KrabbyPatty;
import com.jasonjat.episodeone.item.TestingWand;
import com.jasonjat.episodeone.item.armor.SpongeArmorItem;
import com.jasonjat.episodeone.item.armor.SpongeArmorMaterial;
import com.jasonjat.episodeone.item.tool.CopperAxe;
import com.jasonjat.episodeone.item.tool.CopperPickaxe;
import com.jasonjat.episodeone.util.ModUtil;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.registry.Registry;

public class ItemRegistry {

    public static final ItemGroup MOD_GROUP = FabricItemGroupBuilder.build(ModUtil.id("general"), () -> new ItemStack(Items.SPONGE));

    public static final ArmorMaterial SPONGE_ARMOR_MATERIAL = new SpongeArmorMaterial();
    public static final SpongeArmorItem SPONGE_HELMET = new SpongeArmorItem(SPONGE_ARMOR_MATERIAL, EquipmentSlot.HEAD, new Item.Settings().group(MOD_GROUP));
    public static final SpongeArmorItem SPONGE_CHESTPLATE = new SpongeArmorItem(SPONGE_ARMOR_MATERIAL, EquipmentSlot.CHEST, new Item.Settings().group(MOD_GROUP));
    public static final SpongeArmorItem SPONGE_LEGGINGS = new SpongeArmorItem(SPONGE_ARMOR_MATERIAL, EquipmentSlot.LEGS, new Item.Settings().group(MOD_GROUP));
    public static final SpongeArmorItem SPONGE_BOOTS = new SpongeArmorItem(SPONGE_ARMOR_MATERIAL, EquipmentSlot.FEET, new Item.Settings().group(MOD_GROUP));

    public static final Item COPPER_AXE = new CopperAxe();
    public static final Item COPPER_PICKAXE = new CopperPickaxe();
    public static final Item TESTING_WAND = new TestingWand();

    public static final Item KRABBY_PATTY = new KrabbyPatty();

    public static final Item SPONGEBOB_SPAWN_EGG = new SpawnEggItem(EntityRegistry.SPONGEBOB_BOSS_ENTITY, 16776274, 0, new Item.Settings().group(MOD_GROUP));
    public static final Item DOODLEBOB_SPAWN_EGG = new SpawnEggItem(EntityRegistry.DOODLEBOB_ENTITY, 16777215, 0, new Item.Settings().group(MOD_GROUP));

    public static void register() {
        Registry.register(Registry.ITEM, ModUtil.id("sponge_helmet"), SPONGE_HELMET);
        Registry.register(Registry.ITEM, ModUtil.id("sponge_chestplate"), SPONGE_CHESTPLATE);
        Registry.register(Registry.ITEM, ModUtil.id("sponge_leggings"), SPONGE_LEGGINGS);
        Registry.register(Registry.ITEM, ModUtil.id("sponge_boots"), SPONGE_BOOTS);
        Registry.register(Registry.ITEM, ModUtil.id("copper_axe"), COPPER_AXE);
        Registry.register(Registry.ITEM, ModUtil.id("copper_pickaxe"), COPPER_PICKAXE);
        Registry.register(Registry.ITEM, ModUtil.id("testing_wand"), TESTING_WAND);
        Registry.register(Registry.ITEM, ModUtil.id("krabby_patty"), KRABBY_PATTY);
        Registry.register(Registry.ITEM, ModUtil.id("spongebob_spawn_egg"), SPONGEBOB_SPAWN_EGG);
        Registry.register(Registry.ITEM, ModUtil.id("doodlebob_spawn_egg"), DOODLEBOB_SPAWN_EGG);

    }
}
