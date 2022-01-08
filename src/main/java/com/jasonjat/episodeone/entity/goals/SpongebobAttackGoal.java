package com.jasonjat.episodeone.entity.goals;

import com.jasonjat.episodeone.entity.SpongebobBossEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;

import java.util.EnumSet;


/*
   make a goal where spongebob stops when close enough to player and then plays animation and damages player; an actual sick boss fight
   which doesn't keep following the player and attacking while moving towards player. teleporting etc. attack sequence; reference game boss fights;
   make a separate arena just for fighting the boss.
   https://github.com/Ladysnake/Rats-Mischief/blob/1.18/src/main/java/ladysnake/ratsmischief/common/entity/RatEntity.java
   for animation straight in predicate and using fields for action storing
 */

public class SpongebobAttackGoal extends Goal {

    protected final SpongebobBossEntity spongebob;
    private final double speed;
    private final boolean pauseWhenMobIdle;
    private Path path;
    private double targetX;
    private double targetY;
    private double targetZ;
    private int updateCountdownTicks;
    private int cooldown;
    private long lastUpdateTime;
    private ServerWorld world;

    public SpongebobAttackGoal(SpongebobBossEntity spongebob, double speed, boolean pauseWhenMobIdle) {
        this.spongebob = spongebob;
        this.speed = speed;
        this.pauseWhenMobIdle = pauseWhenMobIdle;
        this.world = (ServerWorld) spongebob.getEntityWorld();
        this.setControls(EnumSet.of(Control.MOVE, Control.LOOK));
    }

    @Override
    public boolean canStart() {

        if (spongebob.performingAttack) return false;

        long worldTime = this.spongebob.world.getTime();

        if (worldTime - lastUpdateTime < 20L) {
            System.out.println(101);
            return false;
        } else {
            lastUpdateTime = worldTime;
            LivingEntity target = spongebob.getTarget();

            // attack range
            if (target == null || !target.isAlive()) {
                System.out.println(102);
                return false;
            } else {
                path = spongebob.getNavigation().findPathTo(target, 0);
                if (path!=null) {
                    System.out.println(103);
                    return true;
                } else {
                    System.out.println(104);
                    return getSquaredMaxAttackDistance() >= spongebob.squaredDistanceTo(target.getX(), target.getY(), target.getZ());
                }
            }
        }
    }

    @Override
    public boolean shouldContinue() {
        LivingEntity target = this.spongebob.getTarget();

        if (target!=null) System.out.println(spongebob.getSqMaxAttackDistance() >= spongebob.squaredDistanceTo(target));
        // goal continues if target is valid & alive and within reachable distance
        if (target == null || !target.isAlive()) {
            return false;
        } else if (spongebob.getSqMaxAttackDistance() >= spongebob.squaredDistanceTo(target)) {
            return false;
        } else if (!pauseWhenMobIdle) {
            return !spongebob.getNavigation().isIdle();
        } else if (!spongebob.isInWalkTargetRange(target.getBlockPos())) {
            return false;
        } else {
            return !(target instanceof PlayerEntity) || !target.isSpectator() && !((PlayerEntity) target).isCreative();
        }
    }

    @Override
    public void start() {

        this.spongebob.getLookControl().lookAt(spongebob.getTarget(), 30, 30);
        spongebob.getNavigation().startMovingTo(spongebob.getTarget(), speed);
        spongebob.setAttacking(true);
        updateCountdownTicks = 0;
        cooldown = 0;
    }

    @Override
    public void stop() {
        LivingEntity target = this.spongebob.getTarget();

        if (!EntityPredicates.EXCEPT_CREATIVE_OR_SPECTATOR.test(target)) {
            this.spongebob.setTarget(null);
        }

        this.spongebob.setAttacking(false);
        this.spongebob.getNavigation().stop();
    }

    @Override
    public void tick() {
        LivingEntity target = this.spongebob.getTarget();
        this.spongebob.getLookControl().lookAt(target, 30, 30);
        assert target != null;
        double distanceToTarget = this.spongebob.squaredDistanceTo(target.getX(), target.getY(), target.getZ());
        this.updateCountdownTicks = Math.max(this.updateCountdownTicks-1, 0);

        if ((this.pauseWhenMobIdle || this.spongebob.getVisibilityCache().canSee(target)) && this.updateCountdownTicks <= 0 && (this.targetX == 0.0D && this.targetY == 0.0D && this.targetZ == 0.0D || target.squaredDistanceTo(this.targetX, this.targetY, this.targetZ) >= 1.0D || this.spongebob.getRandom().nextFloat() < 0.05F)) {
            this.targetX = target.getX();
            this.targetY = target.getY();
            this.targetZ = target.getZ();
            this.updateCountdownTicks = 4 + this.spongebob.getRandom().nextInt(7);

            if (distanceToTarget > 1024.0D) {
                this.updateCountdownTicks += 10;
            } else if (distanceToTarget > 256.0D) {
                this.updateCountdownTicks += 5;
            }

            if (!this.spongebob.getNavigation().startMovingTo(target, this.speed)) {
                this.updateCountdownTicks += 15;
            }
        }

        this.cooldown = Math.max(this.cooldown - 1, 0);
    }

    private double getSquaredMaxAttackDistance() {
        return spongebob.getWidth() * spongebob.getWidth() * 4;
    }
}
