package com.jasonjat.episodeone.entity.goals;

import com.jasonjat.episodeone.entity.SpongebobBossEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.server.world.ServerWorld;

public abstract class BaseSpongebobGoal extends Goal {

    protected final SpongebobBossEntity spongebob;
    protected final ServerWorld world;
    private final int totalGoalTime;
    private final int maxCooldown;
    private final float requiredDistance;
    protected int ticks = 0;
    private int cooldown = 0;

    public BaseSpongebobGoal(SpongebobBossEntity spongebob, int totalGoalTime, int maxCooldown, float requiredDistance) {
        this.spongebob = spongebob;
        this.world = (ServerWorld) spongebob.getEntityWorld();
        this.totalGoalTime = totalGoalTime;
        this.maxCooldown = maxCooldown;
        this.requiredDistance = requiredDistance;
    }

    @Override
    public boolean canStart() {

        if (cooldown > 0) {
            cooldown--;
            return false;
        }

        if (spongebob.getTarget() != null) {
            double distanceToTarget = spongebob.distanceTo(spongebob.getTarget());
            return distanceToTarget <= requiredDistance;
        }

        return false;
    }

    @Override
    public void tick() {
        ticks++;
    }

    @Override
    public boolean shouldContinue() {
        return ticks < totalGoalTime;
    }

    @Override
    public void stop() {
        ticks = 0;
        cooldown = maxCooldown;
    }
}
