package com.jasonjat.episodeone.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.EnumSet;

public class PurpleGolemEntity extends IronGolemEntity implements IAnimatable, IAnimationTickable {
    private final AnimationFactory factory = new AnimationFactory(this);
    private final ServerBossBar bossBar;

    public PurpleGolemEntity(EntityType<? extends IronGolemEntity> entityType, World world) {
        super(entityType, world);
        this.ignoreCameraFrustum = true;
        this.bossBar = (ServerBossBar) (new ServerBossBar(this.getDisplayName(), BossBar.Color.PURPLE, BossBar.Style.PROGRESS)).setDarkenSky(true);
    }

    @Override
    protected void initGoals() {
        goalSelector.add(2, new WanderNearTargetGoal(this, 0.9, 32.0f));
        goalSelector.add(3, new GolemSlamGoal(this));
        goalSelector.add(4, new IronGolemWanderAroundGoal(this, 0.6));
        goalSelector.add(5, new LookAroundGoal(this));

        targetSelector.add(1, new RevengeGoal(this));
        targetSelector.add(2, new ActiveTargetGoal<>(this, MobEntity.class, 5, false, false, entity -> entity instanceof Monster && !(entity instanceof CreeperEntity)));
    }

    public static DefaultAttributeContainer.Builder createEntityAttributes() {
        return LivingEntity.createLivingAttributes().add(EntityAttributes.GENERIC_FOLLOW_RANGE, 16.0).add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2);
    }

    @Override
    protected void mobTick() {
        super.mobTick();
        this.bossBar.setPercent(this.getHealth()/this.getMaxHealth());
    }

    @Override
    public void onStartedTrackingBy(ServerPlayerEntity player) {
        super.onStartedTrackingBy(player);
        this.bossBar.addPlayer(player);
    }

    @Override
    public void onStoppedTrackingBy(ServerPlayerEntity player) {
        super.onStoppedTrackingBy(player);
        this.bossBar.removePlayer(player);
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController<>(this, "controller", 20, this::predicate));
    }

    private <T extends IAnimatable> PlayState predicate(AnimationEvent<T> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.fire_golem.walk", true));
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.fire_golem.idle", true));
        }

        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    @Override
    public int tickTimer() {
        return age;
    }

    private class GolemSlamGoal extends Goal {
        protected final PurpleGolemEntity golem;
        protected final ServerWorld world;
        protected int ticks = 0;
        private int totalGoalTime = 40;
        private int maxCooldown = 60;
        private int cooldown = 0;

        public GolemSlamGoal(PurpleGolemEntity golem) {
            this.golem = golem;
            this.world = (ServerWorld) golem.getEntityWorld();
            setControls(EnumSet.of(Control.MOVE));
        }

        @Override
        public boolean canStart() {
            if (cooldown > 0) {
                cooldown--;
                return false;
            }

            LivingEntity target = getTarget();

            if (target != null) {
                float distance = golem.distanceTo(target);
                return distance <= 10;
            }

            return false;
        }

        @Override
        public void start() {
            System.out.println("play slam animation!");
        }

        @Override
        public void tick() {
            ticks++;

            if (ticks==20) {

                Vec3d outpos = golem.getPos().add(golem.getRotationVector().multiply(5,0,5));
                BlockPos p = new BlockPos(outpos);

                world.getEntitiesByClass(LivingEntity.class, new Box(p).expand(4,2,4), e -> e!=golem).forEach(entity -> {
                    Vec3d difference = entity.getPos().subtract(outpos).normalize();
                    difference = new Vec3d(difference.getX(), Math.max(0.2, difference.getY()), difference.getZ());

                    double distanceToGolem = entity.squaredDistanceTo(outpos);
                    entity.damage(DamageSource.GENERIC, 1);
                    entity.setVelocity(difference);
                    entity.velocityModified = true;
                    entity.velocityDirty = true;
                });

                world.spawnParticles(ParticleTypes.EXPLOSION, p.getX(), p.getY(), p.getZ(), 5, 3, 1, 3, 0);
            }
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
}
