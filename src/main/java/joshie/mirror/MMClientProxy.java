package joshie.mirror;


import joshie.mirror.render.JewelryLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.MinecraftForge;

public class MMClientProxy extends MMCommonProxy {
    @Override
    public void preInit() {
        super.preInit();
        MinecraftForge.EVENT_BUS.register(JewelryLoader.INSTANCE);
        ModelLoaderRegistry.registerLoader(JewelryLoader.INSTANCE);
    }
}