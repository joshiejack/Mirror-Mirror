package joshie.mirror.api;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;

import java.util.HashMap;
import java.util.Map;

public class Ability {
    public static final Map<ResourceLocation, Ability> ABILITIES = new HashMap<>();
    public static final Ability NULL = new Ability(new ResourceLocation("mirrormirror", "null"), false);

    public Ability(ResourceLocation resource, boolean events) {
        ABILITIES.put(resource, this);
        if (events) MinecraftForge.EVENT_BUS.register(this);
    }
}
