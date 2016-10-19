package joshie.mirror.helpers;

import joshie.mirror.api.Ability;
import joshie.mirror.api.Jewelry;
import joshie.mirror.init.Elements;
import joshie.mirror.init.Items;
import joshie.mirror.jewelry.Ring;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class JewelryHelper {
    public static List<Ability> getAbilitiesFromStack(ItemStack stack) {
        return new ArrayList<>();
    }

    public static Jewelry getJewelryFromStack(ItemStack stack) {
        if (stack.getItem() == Items.RING) return Items.RING.getRingFromStack(stack);
        return new Ring(Elements.GOLD, Elements.PEARL_BLACK);
    }
}
