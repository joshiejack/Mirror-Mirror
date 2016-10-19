package joshie.mirror.jewelry;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import joshie.mirror.api.Ability;
import joshie.mirror.api.Jewelry;
import joshie.mirror.api.elements.Band;
import joshie.mirror.api.elements.Ornament;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Ring extends Jewelry {
    private final Band band;
    private final Ornament ornament;

    public Ring(Band band, Ornament ornament) {
        this.band = band;
        this.ornament = ornament;
    }

    @Override
    public void rebuildAbilities(List<Ability> newAbilities) {
        super.rebuildAbilities(newAbilities);
        abilities.add(band.getAbility());
        abilities.add(ornament.getAbility());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Collection<ResourceLocation> getTexturesForRendering() {
        ImmutableSet.Builder<ResourceLocation> builder = ImmutableSet.builder();
        builder.add(new ResourceLocation(band.getResource().getResourceDomain(), "items/jewelry/ring/band_" + band.getResource().getResourcePath()));
        builder.add(new ResourceLocation(ornament.getResource().getResourceDomain(), "items/jewelry/ring/ornament_" + ornament.getResource().getResourcePath()));
        return builder.build();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addToMapForRendering(Map<String, String> map) {
        map.put("band", band.getResource().toString());
        map.put("ornament", ornament.getResource().toString());
    }

    @SideOnly(Side.CLIENT)
    public static Ring getRingFromDataForRendering(ImmutableMap<String, String> customData) {
        ResourceLocation bandLocation = new ResourceLocation(customData.get("band"));
        ResourceLocation ornamentLocation = new ResourceLocation(customData.get("ornament"));
        return new Ring(Band.BANDS.get(bandLocation), Ornament.ORNAMENTS.get(ornamentLocation));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ring ring = (Ring) o;
        if (band != null ? !band.equals(ring.band) : ring.band != null) return false;
        return ornament != null ? ornament.equals(ring.ornament) : ring.ornament == null;

    }

    @Override
    public int hashCode() {
        int result = band != null ? band.hashCode() : 0;
        result = 31 * result + (ornament != null ? ornament.hashCode() : 0);
        return result;
    }
}
