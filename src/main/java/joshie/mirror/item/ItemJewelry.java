package joshie.mirror.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

import static joshie.mirror.lib.MMInfo.MODID;

public class ItemJewelry<J extends ItemJewelry> extends Item {
    public ItemJewelry() {
        setCreativeTab(CreativeTabs.TOOLS);
    }

    @SuppressWarnings("unchecked")
    public J register(String name) {
        setRegistryName(new ResourceLocation(MODID, name));
        setUnlocalizedName(name);
        GameRegistry.register(this);
        if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
            ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
        }

        return (J) this;
    }
}
