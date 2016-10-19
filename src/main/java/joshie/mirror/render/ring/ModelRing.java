package joshie.mirror.render.ring;

import com.google.common.collect.ImmutableMap;
import joshie.mirror.init.Elements;
import joshie.mirror.jewelry.Ring;

public final class ModelRing extends ModelMM<Ring> {
    public static final ModelRing MODEL = new ModelRing(new Ring(Elements.GOLD, Elements.PEARL_BLACK));

    public ModelRing(Ring ring) {
        super(ring);
    }

    @Override
    public ModelRing process(ImmutableMap<String, String> customData) {
        return new ModelRing(Ring.getRingFromDataForRendering(customData));
    }
}
