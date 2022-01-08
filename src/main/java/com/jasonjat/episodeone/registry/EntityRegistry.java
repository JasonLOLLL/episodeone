package com.jasonjat.episodeone.registry;

import com.jasonjat.episodeone.entity.CopperGolemEntity;
import com.jasonjat.episodeone.entity.DoodlebobEntity;
import com.jasonjat.episodeone.entity.PurpleGolemEntity;
import com.jasonjat.episodeone.entity.SpongebobBossEntity;
import com.jasonjat.episodeone.entity.projectile.CircleBlastEntity;
import com.jasonjat.episodeone.entity.projectile.PizzaBoxEntity;
import com.jasonjat.episodeone.util.ModUtil;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.util.registry.Registry;

public class EntityRegistry {
    public static final EntityType<CopperGolemEntity> COPPER_GOLEM_ENTITY =  Registry.register(
            Registry.ENTITY_TYPE,
            ModUtil.id("copper_golem"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, CopperGolemEntity::new).dimensions(EntityDimensions.fixed(0.5F, 0.5F)).build()
    );

    public static final EntityType<PurpleGolemEntity> PURPLE_GOLEM_ENTITY =  Registry.register(
            Registry.ENTITY_TYPE,
            ModUtil.id("purple_golem"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, PurpleGolemEntity::new).dimensions(EntityDimensions.fixed(1F, 2F)).build()
    );

    public static final EntityType<SpongebobBossEntity> SPONGEBOB_BOSS_ENTITY =  Registry.register(
            Registry.ENTITY_TYPE,
            ModUtil.id("spongebob_boss"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, SpongebobBossEntity::new).dimensions(EntityDimensions.fixed(1.75F, 3.9F)).build()
    );

    public static final EntityType<CircleBlastEntity> CIRCLE_BLAST_ENTITY = Registry.register(
            Registry.ENTITY_TYPE,
            ModUtil.id("circle_blast"),
            FabricEntityTypeBuilder.<CircleBlastEntity>create(SpawnGroup.MISC, CircleBlastEntity::new).dimensions(EntityDimensions.fixed(0.25F, 0.25F)).trackRangeBlocks(4).trackedUpdateRate(10).build()
    );

    public static final EntityType<PizzaBoxEntity> PIZZA_BOX_ENTITY = Registry.register(
            Registry.ENTITY_TYPE,
            ModUtil.id("pizza_box"),
            FabricEntityTypeBuilder.<PizzaBoxEntity>create(SpawnGroup.MISC, PizzaBoxEntity::new).dimensions(EntityDimensions.fixed(0.25F, 0.25F)).trackRangeBlocks(4).trackedUpdateRate(10).build()
    );

    public static final EntityType<DoodlebobEntity> DOODLEBOB_ENTITY = Registry.register(
            Registry.ENTITY_TYPE,
            ModUtil.id("doodlebob"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, DoodlebobEntity::new).dimensions(EntityDimensions.fixed(1F, 1.1F)).build()
    );


    public static void register() {
        FabricDefaultAttributeRegistry.register(COPPER_GOLEM_ENTITY, CopperGolemEntity.createMobAttributes().add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 10).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25));
        FabricDefaultAttributeRegistry.register(PURPLE_GOLEM_ENTITY, PurpleGolemEntity.createIronGolemAttributes());
        FabricDefaultAttributeRegistry.register(SPONGEBOB_BOSS_ENTITY, SpongebobBossEntity.createSpongebobAttributes());
        FabricDefaultAttributeRegistry.register(DOODLEBOB_ENTITY, DoodlebobEntity.createDoodlebobAttributes());
    }
}
