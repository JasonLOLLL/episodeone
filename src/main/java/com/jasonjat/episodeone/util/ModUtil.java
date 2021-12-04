package com.jasonjat.episodeone.util;

import net.minecraft.util.Identifier;

import static com.jasonjat.episodeone.Episodeone.MODID;

public class ModUtil {
    public static Identifier id(String string) {
        return new Identifier(MODID, string);
    }
}
