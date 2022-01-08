package com.jasonjat.episodeone.entity.goals;

import com.jasonjat.episodeone.entity.SpongebobBossEntity;
import net.minecraft.command.argument.EntityAnchorArgumentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.server.world.ServerWorld;

public abstract class SpongebobGoal extends Goal {
    protected final SpongebobBossEntity spongebob;
    protected final ServerWorld world;
    public long lastTimeAttacked; //todo change these to private
    public final int cooldown;
    protected final int goalTime;
    protected int ticks;
    private final float maxAttackDistance;

    public SpongebobGoal(SpongebobBossEntity spongebob, int cooldown, int goalTime, float maxAttackDistance) {
        this.spongebob = spongebob;
        this.world = (ServerWorld) spongebob.getEntityWorld();
        this.cooldown = cooldown;
        this.goalTime = goalTime;
        this.maxAttackDistance = maxAttackDistance;
    }

    @Override
    public boolean canStart() {
        // Check if this goal's cooldown is ready
        if (world.getTime() - lastTimeAttacked <= cooldown) {
            return false;
        }

        // Intermission interval between attacks, otherwise attacks will be constantly running one after another
        if (!spongebob.performingAttack) {
            if (spongebob.getTarget() != null && spongebob.getTarget().isAlive()) {
                // Check distance and if distance requirement is met, return true
                return getMaxAttackDistance() >= spongebob.distanceTo(spongebob.getTarget());
            }
        } else {
            return false;
        }

        return false;
    }

    @Override
    public void start() {
        // When canStart() returns true, this runs once; setup animation; reset cooldown
        playAnimation();
        spongebob.lookAt(EntityAnchorArgumentType.EntityAnchor.EYES, spongebob.getTarget().getPos());
        spongebob.performingAttack = true;
    }

    public abstract void playAnimation();

    @Override
    public void tick() {
        // Attack logic
        ticks++;
        LivingEntity target = spongebob.getTarget();

        if (target != null) {
            spongebob.lookAt(EntityAnchorArgumentType.EntityAnchor.EYES, target.getPos());
            attack(target);
        }
    }

    public abstract void attack(LivingEntity target);

    @Override
    public boolean shouldContinue() {
        // Checks every tick
        return ticks < goalTime;
    }

    @Override
    public void stop() {
        ticks = 0;
        spongebob.performingAttack = false;
        spongebob.lastAttackTime = world.getTime();
        lastTimeAttacked = world.getTime();
    }

    // Get the furthest attack distance that this goal can reach
    public float getMaxAttackDistance() {
        return maxAttackDistance;
    }
}
