package com.jasonjat.episodeone.util;

import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;

import java.util.ArrayDeque;
import java.util.Queue;

public class AnimationQueue {

    private final Queue<String> queue = new ArrayDeque<>();
    private final LivingEntity entity;
    private final World world;
    private String current = null;

    public AnimationQueue(LivingEntity entity) {
        this.entity = entity;
        this.world = entity.world;
    }

    public void queueAnimation(String animation) {
        if (world.isClient) {
            queue.add(animation);
        }
    }

    public <T extends IAnimatable>PlayState tick(AnimationEvent<T> event, AnimationController.IAnimationPredicate<T> fallback) {
        if (!queue.isEmpty()) {
            current = queue.poll();

            event.getController().setAnimation(new AnimationBuilder().addAnimation(current));
            event.getController().markNeedsReload();
            return PlayState.CONTINUE;
        }

        if (current!=null) {
            if (event.getController().getAnimationState().equals(AnimationState.Stopped)) {
                current = null;
                event.getController().clearAnimationCache();
            }
            return PlayState.CONTINUE;
        }

        return fallback.test(event);
    }
}
