package com.jasonjat.episodeone.util;

import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static com.jasonjat.episodeone.Episodeone.MODID;

public class ModUtil {
    public static Identifier id(String string) {
        return new Identifier(MODID, string);
    }
    public static void playSound(World world, BlockPos pos, SoundEvent sound) {
        world.playSound(null, pos, sound, SoundCategory.HOSTILE, 1f, 1f);
    }
}
