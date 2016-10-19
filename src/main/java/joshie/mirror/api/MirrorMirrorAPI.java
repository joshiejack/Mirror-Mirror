package joshie.mirror.api;

import joshie.mirror.api.elements.Band;
import joshie.mirror.api.elements.Cord;
import joshie.mirror.api.elements.Ornament;
import net.minecraft.item.ItemStack;

public class MirrorMirrorAPI {
    public static API instance = null;

    public interface API {
        Band createBand(String name, Ability ability);
        Cord createCord(String name, Ability ability);
        Ornament createOrnament(String name, Ability ability);
        boolean hasAbility(ItemStack stack, Ability ability);
    }
}
