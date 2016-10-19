package joshie.mirror.api.elements;

import joshie.mirror.api.Ability;
import joshie.mirror.api.Element;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class Ornament extends Element<Ornament> {
    public static final Map<ResourceLocation, Ornament> ORNAMENTS = new HashMap<>();

    public Ornament(ResourceLocation name, Ability ability) {
        super(name, ability);
    }

    public String getPrefix() {
        return "ornament";
    }

    public Map<ResourceLocation, Ornament> getMap() {
        return ORNAMENTS;
    }
}
