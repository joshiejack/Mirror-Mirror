package joshie.mirror.render.ring;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import joshie.mirror.api.Jewelry;
import joshie.mirror.render.BakedMM;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.*;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.model.TRSRTransformation;

import javax.vecmath.Vector3f;
import java.util.Collection;

public abstract class ModelMM<J extends Jewelry> implements IModel, IModelCustomData {
    public static final SimpleModelState STATE = getModelState();
    private final Collection<ResourceLocation> textures;

    ModelMM(J jewelery) {
        this.textures = jewelery.getTexturesForRendering();
    }

    @Override
    public Collection<ResourceLocation> getDependencies() {
        return ImmutableList.of();
    }

    @Override
    public IBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
        ImmutableMap<TransformType, TRSRTransformation> transformMap = IPerspectiveAwareModel.MapWrapper.getTransforms(state);
        ImmutableList.Builder<BakedQuad> builder = ImmutableList.builder();
        addResourceToBuilder(builder, format, bakedTextureGetter, getTextures());
        return new BakedMM(this, builder.build(), format, transformMap);
    }

    void addResourceToBuilder(ImmutableList.Builder<BakedQuad> builder, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter, Collection<ResourceLocation> resources) {
        for (ResourceLocation resource: resources) {
            if (resource != null) {
                IBakedModel model = (new ItemLayerModel(ImmutableList.of(resource))).bake(STATE, format, bakedTextureGetter);
                builder.addAll(model.getQuads(null, null, 0));
            }
        }
    }

    @Override
    public Collection<ResourceLocation> getTextures() {
        return textures;
    }

    @Override
    public IModelState getDefaultState() {
        return TRSRTransformation.identity();
    }

    private static SimpleModelState getModelState() {
        ImmutableMap.Builder<TransformType, TRSRTransformation> builder = ImmutableMap.builder();
        builder.put(TransformType.GROUND, getTransformation(0, 2, 0, 0, 0, 0, 0.5f));
        builder.put(TransformType.HEAD, getTransformation(0, 13, 7, 0, 180, 0, 1));
        builder.put(TransformType.FIRST_PERSON_RIGHT_HAND, getTransformation(0F, 1.6F, 0.8F, 0F, 90F, 25F, 0.68F));
        builder.put(TransformType.FIRST_PERSON_LEFT_HAND, getTransformation(0F, 1.6F, 0.8F, 0F, -90F, -25F, 0.68F));
        builder.put(TransformType.THIRD_PERSON_RIGHT_HAND, getTransformation(0F, 4F, 2.5F, 0F, 90F, 55F, 0.85F));
        builder.put(TransformType.THIRD_PERSON_LEFT_HAND, getTransformation(0F, 4F, 2.5F, 0F, -90F, -55F, 0.85F));
        return new SimpleModelState(builder.build());
    }

    private static TRSRTransformation getTransformation(float tx, float ty, float tz, float ax, float ay, float az, float s) {
        return TRSRTransformation.blockCenterToCorner(new TRSRTransformation(new Vector3f(tx / 16, ty / 16, tz / 16), TRSRTransformation.quatFromXYZDegrees(new Vector3f(ax, ay, az)), new Vector3f(s, s, s), null));
    }
}
