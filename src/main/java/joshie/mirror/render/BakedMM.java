package joshie.mirror.render;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.model.IModelCustomData;
import net.minecraftforge.client.model.IPerspectiveAwareModel;
import net.minecraftforge.common.model.TRSRTransformation;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.vecmath.Matrix4f;
import java.util.List;

public class BakedMM implements IBakedModel, IPerspectiveAwareModel {
    private final IModelCustomData parent;
    private final ImmutableMap<TransformType, TRSRTransformation> transforms;
    private final ImmutableList<BakedQuad> quads;
    protected final VertexFormat format;

    public BakedMM(IModelCustomData parent, ImmutableList<BakedQuad> quads, VertexFormat format, ImmutableMap<TransformType, TRSRTransformation> transforms) {
        this.parent = parent;
        this.quads = quads;
        this.format = format;
        this.transforms = transforms;
    }

    public VertexFormat getFormat() {
        return format;
    }

    public IModelCustomData getParent() {
        return parent;
    }

    @Override
    @Nonnull
    public ItemOverrideList getOverrides() {
        return MMOverride.INSTANCE;
    }

    @Override
    public Pair<? extends IBakedModel, Matrix4f> handlePerspective(TransformType cameraTransformType) {
        return MapWrapper.handlePerspective(this, transforms, cameraTransformType);
    }

    @Override
    @Nonnull
    public List<BakedQuad> getQuads(@Nullable IBlockState state, @Nullable EnumFacing side, long rand) {
        if (side == null) return quads;
        return ImmutableList.of();
    }

    @Override
    public boolean isAmbientOcclusion() {
        return true;
    }

    @Override
    public boolean isGui3d() {
        return false;
    }

    @Override
    public boolean isBuiltInRenderer() {
        return false;
    }

    @Override
    public TextureAtlasSprite getParticleTexture() {
        return null;
    }

    @Override
    public ItemCameraTransforms getItemCameraTransforms() {
        return ItemCameraTransforms.DEFAULT;
    }
}
