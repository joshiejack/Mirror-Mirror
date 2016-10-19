package joshie.mirror.api;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public abstract class Jewelry {
    protected final List<Ability> abilities;

    public Jewelry() {
        this.abilities = new ArrayList<>();
    }

    public void rebuildAbilities(List<Ability> newAbilities) {
        abilities.clear();
        abilities.addAll(newAbilities);
    }

    @SideOnly(Side.CLIENT)
    public abstract Collection<ResourceLocation> getTexturesForRendering();

    @SideOnly(Side.CLIENT)
    public abstract void addToMapForRendering(Map<String, String> map);
}
