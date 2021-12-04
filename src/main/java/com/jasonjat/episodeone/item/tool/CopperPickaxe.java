package com.jasonjat.episodeone.item.tool;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CopperPickaxe extends PickaxeItem {
    public CopperPickaxe() {
        super(ToolMaterials.GOLD, 5, 2, new Item.Settings().group(ItemGroup.TOOLS).maxDamage(800));
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        world.addParticle(ParticleTypes.DRAGON_BREATH, pos.getX(), pos.getY(), pos.getZ(), 0, 0, 0);

        return super.postMine(stack, world, state, pos, miner);
    }
}
