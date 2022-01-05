package com.jasonjat.episodeone.entity.goals;

import com.jasonjat.episodeone.entity.SpongebobBossEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

import java.util.EnumSet;

public class SpongebobJumpAttackGoal extends SpongebobGoal {
    private boolean jumpAttacked;
    private boolean circleInitiated;

    public SpongebobJumpAttackGoal(SpongebobBossEntity spongebob, int cooldown, int goalTime, float maxAttackDistance) {
        super(spongebob, cooldown, goalTime, maxAttackDistance);
        setControls(EnumSet.of(Control.MOVE, Control.LOOK, Control.JUMP));
    }

    @Override
    public void playAnimation() {
        spongebob.getQueue().queueAnimation(SpongebobBossEntity.ANIMATION_JUMP);
    }

    @Override
    public void attack(LivingEntity target) {
        assert target != null;
        if (ticks == 5) {
            spongebob.addVelocity(0, 3, 0);
        }

        if (ticks == 30) {
            spongebob.teleport(target.getX(), target.getY() + 5, target.getZ(), true);
            double circle = 2 * Math.PI;
            double radius = 15;
            double circumference = circle * radius;
            double points = circumference/0.25;

            for (double v = 0; v<radius; v+=0.25) {
                for (int i = 0; i < points; i++) {
                    double angle = circle / points * i;
                    double x = Math.cos(angle) * v + spongebob.getX();
                    double z = Math.sin(angle) * v + spongebob.getZ();

                    world.spawnParticles(ParticleTypes.SOUL, x, spongebob.getY(), z, 1, 0, 0, 0, 0+v/radius);
                }
            }

            target.damage(DamageSource.GENERIC, 7F);
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 100), spongebob);
        }
    }
}
