package joshie.mirror.render;

import com.google.common.base.Function;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import joshie.mirror.api.Jewelry;
import joshie.mirror.helpers.JewelryHelper;
import joshie.mirror.render.ring.ModelMM;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.IModel;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public final class MMOverride extends ItemOverrideList {
    public static final MMOverride INSTANCE = new MMOverride();
    private final Function<ResourceLocation, TextureAtlasSprite> getter = (location) -> Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(location.toString());
    private final Cache<Jewelry, IBakedModel> cache = CacheBuilder.newBuilder().maximumSize(256).build();

    private MMOverride() {
        super(ImmutableList.of());
    }

    @Override
    @SuppressWarnings("ConstantConditions")
    @Nonnull
    public IBakedModel handleItemState(@Nonnull IBakedModel originalModel, @Nonnull ItemStack stack, @Nonnull World world, @Nonnull EntityLivingBase entityy) {
        Jewelry jewelry = JewelryHelper.getJewelryFromStack(stack);
        if (jewelry == null) {
            return originalModel;
        }

        BakedMM model = (BakedMM) originalModel;
        try {
            return cache.get(jewelry, () -> {
                Map<String, String> map = new HashMap<>();
                jewelry.addToMapForRendering(map);
                IModel parent = model.getParent().process(ImmutableMap.copyOf(map));
                return parent.bake(ModelMM.STATE, model.getFormat(), getter);
            });
        } catch (ExecutionException ex) { return originalModel; }
    }
}
