package joshie.mirror.init;

import joshie.mirror.api.Ability;
import joshie.mirror.api.MirrorMirrorAPI;
import joshie.mirror.api.elements.Band;
import joshie.mirror.api.elements.Ornament;

public class Elements {
    public static final Band ONE_RING = MirrorMirrorAPI.instance.createBand("one_ring", Abilities.INVISIBILITY).setNoOrnaments();
    public static final Band GOLD = MirrorMirrorAPI.instance.createBand("gold", Ability.NULL);
    public static final Ornament NULL = MirrorMirrorAPI.instance.createOrnament("null", Ability.NULL);
    public static final Ornament PEARL_BLACK = MirrorMirrorAPI.instance.createOrnament("pearl_black", Ability.NULL);

    public static void init() { }
}
