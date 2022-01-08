package com.jasonjat.episodeone.entity.goals;

import com.jasonjat.episodeone.entity.DoodlebobEntity;
import com.jasonjat.episodeone.entity.SpongebobBossEntity;
import com.jasonjat.episodeone.registry.EntityRegistry;
import net.minecraft.entity.LivingEntity;

import java.util.EnumSet;

public class SpongebobMinionGoal extends SpongebobGoal {
    public SpongebobMinionGoal(SpongebobBossEntity spongebob, int cooldown, int goalTime, float maxAttackDistance) {
        super(spongebob, cooldown, goalTime, maxAttackDistance);
        setControls(EnumSet.of(Control.MOVE, Control.LOOK));
    }

    @Override
    public void playAnimation() {
        spongebob.getQueue().queueAnimation(SpongebobBossEntity.ANIMATION_SUMMON);
    }

    @Override
    public void attack(LivingEntity target) {
        int amt = 4;
        int radius = 2;
        final double circle = Math.PI*2;

        for (int i = 0; i<amt; i++) {
            double angle = circle / amt * i;
            double x = Math.cos(angle) * radius + spongebob.getX();
            double z = Math.sin(angle) * radius + spongebob.getZ();

            DoodlebobEntity doodlebob = new DoodlebobEntity(EntityRegistry.DOODLEBOB_ENTITY, world);
            doodlebob.setPos(x, spongebob.getY() + 1, z);
            doodlebob.setTarget(target);
            doodlebob.isSpawnedByBoss = true;
            world.spawnEntity(doodlebob);
        }
    }
}
