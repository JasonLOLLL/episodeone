package com.jasonjat.episodeone.item;

import com.jasonjat.episodeone.entity.projectile.CircleBlastEntity;
import com.jasonjat.episodeone.entity.projectile.PizzaBoxEntity;
import com.jasonjat.episodeone.registry.EntityRegistry;
import com.jasonjat.episodeone.registry.ItemRegistry;
import com.jasonjat.episodeone.registry.ParticleRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class TestingWand extends Item {
    public int currentSelection = 1;
    public final int maxSelection = 2;

    public TestingWand() {
        super(new FabricItemSettings().group(ItemRegistry.MOD_GROUP).maxCount(1));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        if (!world.isClient) {
            if (user.isSneaking()) {
                currentSelection = currentSelection+1<=maxSelection ? currentSelection+1 : 1;
                user.sendMessage(Text.of("Selected: " + currentSelection).shallowCopy().formatted(Formatting.YELLOW), true);
                return super.use(world, user, hand);
            }

            ServerWorld serverWorld = (ServerWorld) world;
            switch (currentSelection) {
                case 1 -> circleParticleThing(serverWorld, user);
                case 2 -> projectile(serverWorld, user);
            }

        }

        return super.use(world, user, hand);
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        System.out.println(remainingUseTicks);
        super.usageTick(world, user, stack, remainingUseTicks);
    }

    private void circleParticleThing(ServerWorld world, PlayerEntity player) {
        double circle = 2 * Math.PI;
        double radius = 15;
        double circumference = circle * radius;
        double points = circumference/0.25;

        for (double v = 0; v<radius; v+=0.25) {
            for (int i = 0; i < points; i++) {
                double angle = circle / points * i;
                double x = Math.cos(angle) * v + player.getX();
                double z = Math.sin(angle) * v + player.getZ();

                world.spawnParticles(ParticleTypes.SOUL, x, player.getY(), z, 1, 0, 0, 0, 0+v/radius);

            }
        }
    }

    private void projectile(ServerWorld world, PlayerEntity player) {
        PizzaBoxEntity pizzaBox = new PizzaBoxEntity(player, world);
        pizzaBox.setPosition(player.getPos());

        pizzaBox.setVelocity(player, player.getPitch(), player.getYaw(), 0, 1, 0);
        pizzaBox.velocityDirty = true;
        pizzaBox.velocityModified = true;

        world.spawnEntity(pizzaBox);
    }
}
