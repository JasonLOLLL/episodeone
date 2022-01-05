package com.jasonjat.episodeone.entity.projectile;

import com.jasonjat.episodeone.networking.EntitySpawnPacket;
import com.jasonjat.episodeone.registry.EntityRegistry;
import com.jasonjat.episodeone.util.ModUtil;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.Packet;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class CircleBlastEntity extends ThrownItemEntity {

    public static final Identifier SPAWN_PACKET = ModUtil.id("circle_blast_entity_spawn_packet");

    public CircleBlastEntity(EntityType<? extends CircleBlastEntity> entityType, World world) {
        super(entityType, world);
    }

    public CircleBlastEntity(World world, LivingEntity owner) {
        super(EntityRegistry.CIRCLE_BLAST_ENTITY, owner, world);
    }

    public CircleBlastEntity(World world, double x, double y, double z) {
        super(EntityRegistry.CIRCLE_BLAST_ENTITY, x, y, z, world);
    }

    @Override
    protected Item getDefaultItem() {
        return null;
    }

    @Override
    public void tick() {
        if (this.age > 40) {
            this.discard();
        }
        super.tick();
    }

    @Override
    public Packet<?> createSpawnPacket() {
        return EntitySpawnPacket.create(this, SPAWN_PACKET);
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        if (!this.world.isClient) {
            Vec3d pos = hitResult.getPos();
            world.createExplosion(this, pos.x, pos.y, pos.z, 2F, Explosion.DestructionType.DESTROY);
        }
        super.onCollision(hitResult);
    }
}
