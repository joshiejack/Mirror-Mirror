package joshie.mirror;

import joshie.mirror.api.Ability;
import joshie.mirror.api.MirrorMirrorAPI;
import joshie.mirror.api.elements.Band;
import joshie.mirror.api.elements.Cord;
import joshie.mirror.api.elements.Ornament;
import joshie.mirror.helpers.JewelryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;

public class MMAPI implements MirrorMirrorAPI.API {
    @Override
    public Band createBand(String name, Ability ability) {
        ResourceLocation resource = new ResourceLocation(Loader.instance().activeModContainer().getModId(), name);
        return new Band(resource, ability);
    }

    @Override
    public Cord createCord(String name, Ability ability) {
        ResourceLocation resource = new ResourceLocation(Loader.instance().activeModContainer().getModId(), name);
        return new Cord(resource, ability);
    }

    @Override
    public Ornament createOrnament(String name, Ability ability) {
        ResourceLocation resource = new ResourceLocation(Loader.instance().activeModContainer().getModId(), name);
        return new Ornament(resource, ability);
    }

    @Override
    public boolean hasAbility(ItemStack stack, Ability ability) {
        return JewelryHelper.getAbilitiesFromStack(stack).contains(ability);
    }
}
