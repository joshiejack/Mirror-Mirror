package joshie.mirror;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static joshie.mirror.lib.MMInfo.*;


@Mod(modid = MODID, name = MODNAME, version = VERSION)
public class MirrorMirror {
    public static final Logger logger = LogManager.getLogger(MODNAME);

    @SidedProxy(clientSide = JAVAPATH + "MMClientProxy", serverSide = JAVAPATH + "MMCommonProxy")
    public static MMCommonProxy proxy;

    @Instance(MODID)
    public static MirrorMirror instance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}