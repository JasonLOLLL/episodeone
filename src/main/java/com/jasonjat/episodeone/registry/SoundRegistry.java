package com.jasonjat.episodeone.registry;

import com.jasonjat.episodeone.util.ModUtil;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SoundRegistry {

    public static final Identifier LAUGH_ID = ModUtil.id("laugh");
    public static SoundEvent LAUGH_EVENT = new SoundEvent(LAUGH_ID);
    public static final Identifier KRUSTYKRABPIZZA_ID = ModUtil.id("krustykrabpizza");
    public static SoundEvent KRUSTYKRABPIZZA_EVENT = new SoundEvent(KRUSTYKRABPIZZA_ID);
    public static final Identifier KRUSTYKRABPIZZA2_ID = ModUtil.id("krustykrab2pizza");
    public static SoundEvent KRUSTYKRABPIZZA2_EVENT = new SoundEvent(KRUSTYKRABPIZZA2_ID);

    public static void register() {
        Registry.register(Registry.SOUND_EVENT, LAUGH_ID, LAUGH_EVENT);
        Registry.register(Registry.SOUND_EVENT, KRUSTYKRABPIZZA_ID, KRUSTYKRABPIZZA_EVENT);
        Registry.register(Registry.SOUND_EVENT, KRUSTYKRABPIZZA2_ID, KRUSTYKRABPIZZA2_EVENT);
    }
}
