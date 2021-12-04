package com.jasonjat.episodeone.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import software.bernie.example.entity.RocketProjectile;

public class CopperAxeProjectileEntity extends RocketProjectile {
    {

    }

    public CopperAxeProjectileEntity(EntityType<? extends RocketProjectile> entityType, World world) {
        super(entityType, world);
    }

    public CopperAxeProjectileEntity(World world, LivingEntity owner) {
        super(world, owner);
    }

    protected CopperAxeProjectileEntity(EntityType<? extends RocketProjectile> type, double x, double y, double z, World world) {
        super(type, x, y, z, world);
    }

    protected CopperAxeProjectileEntity(EntityType<? extends RocketProjectile> type, LivingEntity owner, World world) {
        super(type, owner, world);
    }

}
