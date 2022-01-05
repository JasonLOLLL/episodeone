package com.jasonjat.episodeone.entity.goals;

import com.jasonjat.episodeone.entity.SpongebobBossEntity;
import com.jasonjat.episodeone.entity.projectile.PizzaBoxEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;

import java.util.EnumSet;

public class SpongebobPizzaGoal extends SpongebobGoal {
    public SpongebobPizzaGoal(SpongebobBossEntity spongebob, int cooldown, int goalTime, float maxAttackDistance) {
        super(spongebob, cooldown, goalTime, maxAttackDistance);
        setControls(EnumSet.of(Control.MOVE, Control.LOOK));
    }

    @Override
    public void playAnimation() {
        spongebob.getQueue().queueAnimation(SpongebobBossEntity.ANIMATION_THROW);
    }

    @Override
    public void attack(LivingEntity target) {
        if (ticks%10==0) {
            PizzaBoxEntity pizzaBox = new PizzaBoxEntity(spongebob, world);
            pizzaBox.setPosition(spongebob.getPos().add(0, 1.5, 0));
            Vec3d velocityVector = target.getPos().subtract(spongebob.getPos()).normalize(); // subtract the target's pos from spongebob to get the vector
            pizzaBox.setVelocity(velocityVector);
            pizzaBox.velocityDirty = true;
            pizzaBox.velocityModified = true;
            world.spawnEntity(pizzaBox);
            playAnimation();
        }
    }
}
