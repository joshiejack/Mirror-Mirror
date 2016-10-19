package joshie.mirror;

import joshie.mirror.api.MirrorMirrorAPI;
import joshie.mirror.init.Abilities;
import joshie.mirror.init.Elements;
import joshie.mirror.init.Items;

public class MMCommonProxy {
    public void preInit() {
        MirrorMirrorAPI.instance = new MMAPI();

        Items.init();
        Abilities.init();
        Elements.init();
    }
}