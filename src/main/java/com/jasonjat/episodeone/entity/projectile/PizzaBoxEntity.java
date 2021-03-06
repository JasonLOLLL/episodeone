package com.jasonjat.episodeone.entity.projectile;

import com.jasonjat.episodeone.registry.EntityRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class PizzaBoxEntity extends PersistentProjectileEntity implements IAnimatable {
    public final AnimationFactory factory = new AnimationFactory(this);
    public static final String IDLE_ANIMATION = "animation.pizza_box.idle";
    public static final String OPEN_ANIMATION = "animation.pizza_box.open";

    public PizzaBoxEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public PizzaBoxEntity(double x, double y, double z, World world) {
        super(EntityRegistry.PIZZA_BOX_ENTITY, x, y, z, world);
    }

    public PizzaBoxEntity(LivingEntity owner, World world) {
        super(EntityRegistry.PIZZA_BOX_ENTITY, owner, world);
    }

    @Override
    protected ItemStack asItemStack() {
        return new ItemStack(Items.ARROW);
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController<>(this, "controller", 20, this::predicate));
    }

    private PlayState predicate(AnimationEvent<PizzaBoxEntity> event) {
        if (age < 5 && !collidedSoftly) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation(IDLE_ANIMATION));
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation(OPEN_ANIMATION));
        }
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    @Override
    public boolean hasNoGravity() {
        return true;
    }

    @Override
    public void tick() {
        if (age > 60) {
            if (!world.isClient) {
                ((ServerWorld) world).spawnParticles(ParticleTypes.FLAME, getX(), getY(), getZ(), 10, 0,0 ,0, 0);
            }
            this.discard();
        }

        super.tick();
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        if (!world.isClient && getOwner()!=null && !getOwner().equals(entityHitResult.getEntity())) {
            entityHitResult.getEntity().setOnFireFor(3);
            explode(getPos());
            this.discard();
        }

        super.onEntityHit(entityHitResult);
    }

    @Override
    protected void onBlockCollision(BlockState state) {

        if (!world.isClient && !state.isAir()) {
            explode(getPos());
            this.discard();
        }

        super.onBlockCollision(state);
    }

    private void explode(Vec3d pos) {
        world.createExplosion(this, pos.x, pos.y, pos.z, 1, Explosion.DestructionType.BREAK);
    }


}
