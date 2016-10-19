package joshie.mirror.jewelry;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import joshie.mirror.api.Ability;
import joshie.mirror.api.Jewelry;
import joshie.mirror.api.elements.Cord;
import joshie.mirror.api.elements.Ornament;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Necklace extends Jewelry {
    private final Cord cord;
    private final Ornament[] ornaments;

    public Necklace(Cord cord, Ornament... ornaments) {
        this.cord = cord;
        this.ornaments = ornaments;
    }

    @Override
    public void rebuildAbilities(List<Ability> newAbilities) {
        super.rebuildAbilities(newAbilities);
        abilities.add(cord.getAbility());
        for (Ornament ornament: ornaments) abilities.add(ornament.getAbility());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Collection<ResourceLocation> getTexturesForRendering() {
        ImmutableSet.Builder<ResourceLocation> builder = ImmutableSet.builder();
        builder.add(new ResourceLocation(cord.getResource().getResourceDomain(), "items/jewelry/necklace/cord_" + cord.getResource().getResourcePath()));
        for (Ornament ornament: ornaments) {
            builder.add(new ResourceLocation(ornament.getResource().getResourceDomain(), "items/jewelry/ring/ornament_" + ornament.getResource().getResourcePath()));
        }

        return builder.build();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addToMapForRendering(Map<String, String> map) {
        map.put("cord", cord.getResource().toString());
        map.put("length", "" + ornaments.length);
        for (int i = 0; i < ornaments.length; i++) {
            map.put("ornament" + i, ornaments[i].getResource().toString());
        }
    }

    @SideOnly(Side.CLIENT)
    public static Necklace getNecklaceFromDataForRendering(ImmutableMap<String, String> customData) {
        Cord cord = Cord.CORDS.get(new ResourceLocation(customData.get("cord")));
        int length = Integer.parseInt(customData.get("length"));
        Ornament[] ornaments = new Ornament[length];
        for (int i = 0; i < ornaments.length; i++) {
            ResourceLocation ornamentLocation = new ResourceLocation(customData.get("ornament" + i));
            ornaments[i] = Ornament.ORNAMENTS.get(ornamentLocation);
        }

        return new Necklace(cord, ornaments);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Necklace necklace = (Necklace) o;
        if (cord != null ? !cord.equals(necklace.cord) : necklace.cord != null) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(ornaments, necklace.ornaments);

    }

    @Override
    public int hashCode() {
        int result = cord != null ? cord.hashCode() : 0;
        result = 31 * result + Arrays.hashCode(ornaments);
        return result;
    }
}
