package joshie.mirror.render;

import joshie.mirror.api.Element;
import joshie.mirror.api.elements.Band;
import joshie.mirror.api.elements.Cord;
import joshie.mirror.api.elements.Ornament;
import joshie.mirror.render.ring.ModelRing;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class JewelryLoader implements ICustomModelLoader {
    public static final JewelryLoader INSTANCE = new JewelryLoader();

    private void registerSprites(String type, TextureMap map, Element component) {
        map.registerSprite(new ResourceLocation(component.getResource().getResourceDomain(), "items/jewelry/" + type + "/" + component.getPrefix() + "_" + component.getResource().getResourcePath()));
    }

    @SubscribeEvent
    public void onTextureStitch(TextureStitchEvent.Pre event) {
        TextureMap map = event.getMap();
        for (Band band: Band.BANDS.values()) registerSprites("ring", map, band);
        for (Cord cord: Cord.CORDS.values()) registerSprites("necklace", map, cord);
        for (Ornament ornament: Ornament.ORNAMENTS.values()) {
            registerSprites("ring", map, ornament);
            registerSprites("necklace", map, ornament);
        }
    }

    @Override
    public boolean accepts(ResourceLocation modelLocation) {
        return modelLocation.getResourceDomain().equals("mirrormirror") && (modelLocation.getResourcePath().equals("ring") || modelLocation.getResourcePath().equals("necklace"));
    }

    @Override
    public IModel loadModel(ResourceLocation modelLocation) {
        return ModelRing.MODEL;
    }

    @Override
    public void onResourceManagerReload(@Nonnull IResourceManager resourceManager){}
}
