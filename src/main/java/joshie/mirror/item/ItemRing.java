package joshie.mirror.item;

import joshie.mirror.api.elements.Band;
import joshie.mirror.api.elements.Ornament;
import joshie.mirror.init.Elements;
import joshie.mirror.jewelry.Ring;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

import static joshie.mirror.init.Elements.NULL;

public class ItemRing extends ItemJewelry<ItemRing> {
    private static final Ring DEFAULT = new Ring(Elements.GOLD, Elements.PEARL_BLACK);

    public Ring getRingFromStack(ItemStack stack) {
        if (!stack.hasTagCompound()) return DEFAULT;
        else {
            Band band = Band.BANDS.get(new ResourceLocation(stack.getTagCompound().getString("Band")));
            Ornament ornament = Ornament.ORNAMENTS.get(new ResourceLocation(stack.getTagCompound().getString("Ornament")));
            return band == null || ornament == null ? DEFAULT : new Ring(band, ornament);
        }
    }

    public ItemStack getStackFromRing(Band band, Ornament ornament) {
        ItemStack stack = new ItemStack(this);
        stack.setTagCompound(new NBTTagCompound());
        stack.getTagCompound().setString("Band", band.getResource().toString());
        stack.getTagCompound().setString("Ornament", band.getResource().toString());
        return stack;
    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
        for (Band band: Band.BANDS.values()) {
            if (band.hasOrnaments()) {
                for (Ornament ornament : Ornament.ORNAMENTS.values()) {
                    if (ornament == NULL) continue;
                    else subItems.add(getStackFromRing(band, ornament));
                }
            } else subItems.add(getStackFromRing(band, NULL));
        }
    }
}
