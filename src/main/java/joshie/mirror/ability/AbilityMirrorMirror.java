package joshie.mirror.ability;

import joshie.mirror.api.Ability;
import net.minecraft.util.ResourceLocation;

import static joshie.mirror.lib.MMInfo.MODID;

public class AbilityMirrorMirror extends Ability {
    public AbilityMirrorMirror(String name, boolean events) {
        super(new ResourceLocation(MODID, name), events);
    }
}
