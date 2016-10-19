package joshie.mirror.api.elements;

import joshie.mirror.api.Ability;
import joshie.mirror.api.Element;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class Band extends Element<Band> {
    public static final Map<ResourceLocation, Band> BANDS = new HashMap<>();
    private boolean hasOrnaments = true;

    public Band(ResourceLocation name, Ability ability) {
        super(name, ability);
    }

    public String getPrefix() {
        return "band";
    }

    public Map<ResourceLocation, Band> getMap() {
        return BANDS;
    }

    public Band setNoOrnaments() {
        hasOrnaments = false;
        return this;
    }

    public boolean hasOrnaments() {
        return hasOrnaments;
    }
}
