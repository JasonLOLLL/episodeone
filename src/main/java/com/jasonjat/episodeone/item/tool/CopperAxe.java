package com.jasonjat.episodeone.item.tool;

import com.jasonjat.episodeone.registry.ItemRegistry;
import com.jasonjat.episodeone.registry.ParticleRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterials;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.example.entity.RocketProjectile;

import java.util.ArrayList;
import java.util.List;

public class CopperAxe extends AxeItem {
    public CopperAxe() {
        super(ToolMaterials.GOLD, 8, 1, new Settings().group(ItemRegistry.MOD_GROUP).maxDamage(800));
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        ServerWorld serverWorld = (ServerWorld) target.getEntityWorld();

        serverWorld.spawnParticles(ParticleRegistry.COPPER_FLAME, target.getX(), target.getY(), target.getZ(), 75, 1,1,1, 0.2);
        serverWorld.spawnParticles(ParticleRegistry.BLUE_FLAME, target.getX(), target.getY(), target.getZ(), 75, 1,1,1, 0.2);

        target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 1));
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 100, 1));
        target.addVelocity(0,0.35,0);
        attacker.playSound(SoundEvents.ITEM_FIRECHARGE_USE, 1F, 1F);
        return super.postHit(stack, target, attacker);
    }

    @Override
    public Text getName(ItemStack stack) {
        return new TranslatableText(this.getTranslationKey(stack)).formatted(Formatting.YELLOW);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new TranslatableText("item.eo.copper_axe.tooltip").formatted(Formatting.BOLD, Formatting.GOLD));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world!=null) {
            RocketProjectile rocketProjectile = new RocketProjectile(world, user);
            rocketProjectile.setPosition(rocketProjectile.getPos().add(0,-1,0));
            rocketProjectile.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 3.0F, 1.0F);
            world.spawnEntity(rocketProjectile);
            Vec3d v = new Vec3d(1,1,1);
            v.normalize();
        }
        return super.use(world, user, hand);
    }

    private void raycastWater(World world, PlayerEntity user) {
        if (world!=null && !world.isClient) {
            BlockPos h = new BlockPos(user.raycast(100, 0, true).getPos());
            List<BlockPos> positions = new ArrayList<>();
            for (int i = 2; i<100; i++) {
                BlockPos pos = new BlockPos(user.raycast(i, 0, true).getPos());
                positions.add(pos);
                if (pos.equals(h)) break;
            }

            positions.forEach(x-> world.addParticle(ParticleTypes.DRIPPING_DRIPSTONE_WATER, x.getX(), x.getY(), x.getZ(), 0, 0, 0));
            world.setBlockState(h, Blocks.WATER.getDefaultState());
        }
    }
}
