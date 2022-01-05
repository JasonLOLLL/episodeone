package com.jasonjat.episodeone.entity;

import com.jasonjat.episodeone.entity.goals.*;
import com.jasonjat.episodeone.util.AnimationQueue;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class SpongebobBossEntity extends HostileEntity implements IAnimatable, IAnimationTickable {

    public static final String ANIMATION_IDLE = "animation.spongebob_boss.idle";
    public static final String ANIMATION_SPRINT = "animation.spongebob_boss.sprint";
    public static final String ANIMATION_SLAM = "animation.spongebob_boss.slam";
    public static final String ANIMATION_ATTACK = "animation.spongebob_boss.attack";
    public static final String ANIMATION_JUMP = "animation.spongebob_boss.jump";
    public static final String ANIMATION_THROW = "animation.spongebob_boss.pizza_throw";

    private final AnimationFactory factory = new AnimationFactory(this);
    private final AnimationQueue queue = new AnimationQueue(this);
    private final ServerBossBar bossBar;

    public boolean performingAttack;
    public long lastAttackTime;
    public static final int INTERMISSION_TIME = 20;

    public SpongebobBossEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.ignoreCameraFrustum = true;
        this.bossBar = (ServerBossBar) (new ServerBossBar(this.getDisplayName().shallowCopy().formatted(Formatting.BOLD, Formatting.YELLOW), BossBar.Color.YELLOW, BossBar.Style.PROGRESS)).setDarkenSky(true);
        this.stepHeight = 4F;

        queue.registerAnimation(ANIMATION_SLAM, 0);
        queue.registerAnimation(ANIMATION_ATTACK, 1);
        queue.registerAnimation(ANIMATION_JUMP, 2);
        queue.registerAnimation(ANIMATION_THROW, 3);
    }

    public static DefaultAttributeContainer.Builder createSpongebobAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 64)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 10)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 100)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 10)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3)
                .add(EntityAttributes.GENERIC_ARMOR, 1)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 1)
                .add(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, 2);
    }
    
    @Override
    protected void initGoals() {
        goalSelector.add(0, new SpongebobAttackGoal(this, 1, false));
        goalSelector.add(1, new SpongebobSlamGoal(this, 240, 20, 5));
        goalSelector.add(1, new SpongebobPunchGoal(this, 20, 20, 5));
        goalSelector.add(2, new SpongebobJumpAttackGoal(this, 300, 60, 15));
        goalSelector.add(2, new SpongebobPizzaGoal(this, 60, 100, 15));

        goalSelector.add(5, new LookAroundGoal(this));
        goalSelector.add(5, new WanderAroundGoal(this, 0.6));

        targetSelector.add(1, new RevengeGoal(this));
        targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, false));
    }

    @Override
    public void onStartedTrackingBy(ServerPlayerEntity player) {
        super.onStartedTrackingBy(player);
        bossBar.addPlayer(player);
    }

    @Override
    public void onStoppedTrackingBy(ServerPlayerEntity player) {
        super.onStoppedTrackingBy(player);
        bossBar.removePlayer(player);
    }

    @Override
    protected void mobTick() {
        super.mobTick();
        this.bossBar.setPercent(getHealth()/getMaxHealth());
        // TODO use this tick method as a "scheduler" for the expanding circle
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController<>(this, "controller", 5, event -> queue.tick(event, this::predicate)));
    }

    private <T extends IAnimatable> PlayState predicate(AnimationEvent<T> event) {
        if (event.isMoving() && this.queue.getQueue().isEmpty()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation(ANIMATION_SPRINT, true));
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation(ANIMATION_IDLE, true));
        }
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    @Override
    public void onAttacking(Entity target) {
        super.onAttacking(target);
    }

    @Override
    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        return true;
    }

    @Override
    public int tickTimer() {
        return age;
    }

    public AnimationQueue getQueue() {
        return queue;
    }

    @Override
    public void handleStatus(byte status) {
        super.handleStatus(status);
        queue.handleStatus(status);
    }

    @Override
    public boolean cannotDespawn() {
        return true;
    }

    @Override
    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return 3.6f;
    }

    public float getSqMaxAttackDistance() {
        return (float) Math.pow(15, 2);
    }
}
