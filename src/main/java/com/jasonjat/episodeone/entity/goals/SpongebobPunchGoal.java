package com.jasonjat.episodeone.entity.goals;

import com.jasonjat.episodeone.entity.SpongebobBossEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particle.ParticleTypes;

import java.util.EnumSet;

public class SpongebobPunchGoal extends SpongebobGoal {

    public SpongebobPunchGoal(SpongebobBossEntity spongebob, int maxCooldown, int goalTime, float maxAttackDistance) {
        super(spongebob, maxCooldown, goalTime, maxAttackDistance);
        setControls(EnumSet.of(Control.MOVE, Control.LOOK));
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void playAnimation() {
        spongebob.getQueue().queueAnimation(SpongebobBossEntity.ANIMATION_ATTACK);
    }

    @Override
    public void attack(LivingEntity target) {
        if (ticks == 5) { // At one second
            spongebob.tryAttack(target);
            world.spawnParticles(ParticleTypes.EXPLOSION, target.getX(), target.getY(), target.getZ(), 10, 0,0,0, 0.2);
        }
    }
}
