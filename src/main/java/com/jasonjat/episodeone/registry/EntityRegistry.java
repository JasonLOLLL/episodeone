package com.jasonjat.episodeone.registry;

import com.jasonjat.episodeone.entity.CopperGolemEntity;
import com.jasonjat.episodeone.entity.PurpleGolemEntity;
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

    public static void register() {
        FabricDefaultAttributeRegistry.register(COPPER_GOLEM_ENTITY, CopperGolemEntity.createMobAttributes().add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 10).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25));
        FabricDefaultAttributeRegistry.register(PURPLE_GOLEM_ENTITY, PurpleGolemEntity.createIronGolemAttributes());
    }
}
