package joshie.mirror.api.elements;

import joshie.mirror.api.Ability;
import joshie.mirror.api.Element;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class Cord extends Element<Cord> {
    public static final Map<ResourceLocation, Cord> CORDS = new HashMap<>();

    public Cord(ResourceLocation name, Ability ability) {
        super(name, ability);
    }

    public String getPrefix() {
        return "cord";
    }

    public Map<ResourceLocation, Cord> getMap() {
        return CORDS;
    }
}
