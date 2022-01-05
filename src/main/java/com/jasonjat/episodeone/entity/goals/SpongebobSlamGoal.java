package com.jasonjat.episodeone.entity.goals;

import com.jasonjat.episodeone.entity.SpongebobBossEntity;
import net.minecraft.command.argument.EntityAnchorArgumentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ShieldItem;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import java.util.EnumSet;

public class SpongebobSlamGoal extends SpongebobGoal {
    public SpongebobSlamGoal(SpongebobBossEntity spongebob, int maxCooldown, int goalTime, float maxAttackDistance) {
        super(spongebob, maxCooldown, goalTime, maxAttackDistance);
        setControls(EnumSet.of(Control.MOVE, Control.LOOK));
    }

    @Override
    public void start() {
        super.start();
        System.out.println("Slam started!");
    }

    @Override
    public void playAnimation() {
        spongebob.getQueue().queueAnimation(SpongebobBossEntity.ANIMATION_SLAM);
    }

    @Override
    public void attack(LivingEntity target) {
        if (ticks == 5) { // at 2 seconds
            Vec3d outPos = spongebob.getPos().add(spongebob.getRotationVector().multiply(5,0,5));
            BlockPos out = new BlockPos(outPos);

            world.getEntitiesByClass(LivingEntity.class, new Box(out).expand(4,2,4), livingEntity -> livingEntity != spongebob).forEach(entity -> {
                Vec3d difference = entity.getPos().subtract(outPos).normalize();
                difference = new Vec3d(difference.getX(), Math.max(0.2, difference.getY()), difference.getZ());

                // shield
                boolean hasShield = false;
                if (entity instanceof PlayerEntity player) {
                    hasShield = player.getActiveItem().getItem() instanceof ShieldItem;
                }

                double distanceToSpongebob = entity.squaredDistanceTo(outPos);
                float scalar = (float) Math.max(1.0, 10-distanceToSpongebob);
                entity.damage(DamageSource.GENERIC, scalar);
                entity.setVelocity(difference.multiply(hasShield ? 0.1 : 0.35).multiply(scalar));
                entity.velocityModified = true;
                entity.velocityDirty = true;
            });

            world.spawnParticles(ParticleTypes.EXPLOSION, out.getX(), out.getY(), out.getZ(), 5, 3, 1,3,0);
        }
    }
}
