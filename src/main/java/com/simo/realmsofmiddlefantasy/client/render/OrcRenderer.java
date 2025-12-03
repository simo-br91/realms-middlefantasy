package com.simo.realmsofmiddlefantasy.client.render;

import com.simo.realmsofmiddlefantasy.RealmsOfMiddleFantasy;
import com.simo.realmsofmiddlefantasy.entity.custom.OrcEntity;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.ResourceLocation;

/**
 * Renderer dédié pour l'Orc.
 *
 * - Utilise un HumanoidModel<OrcEntity> (générique)
 * - Layer ORC_LAYER pour pouvoir changer le modèle plus tard
 */
public class OrcRenderer extends HumanoidMobRenderer<OrcEntity, HumanoidModel<OrcEntity>> {

    // Layer custom pour l'Orc
    public static final ModelLayerLocation ORC_LAYER =
            new ModelLayerLocation(
                    new ResourceLocation(RealmsOfMiddleFantasy.MODID, "orc"),
                    "main"
            );

    private static final ResourceLocation ORC_TEXTURE =
            new ResourceLocation(RealmsOfMiddleFantasy.MODID, "textures/entity/orc.png");

    public OrcRenderer(EntityRendererProvider.Context context) {
        // HumanoidModel générique, basé sur ORC_LAYER
        super(context, new HumanoidModel<>(context.bakeLayer(ORC_LAYER)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(OrcEntity entity) {
        return ORC_TEXTURE;
    }

    /**
     * LayerDefinition pour ORC_LAYER.
     *
     * Pour l'instant, on réutilise la mesh de base des humanoïdes vanilla.
     * Plus tard, tu pourras remplacer ça par un modèle custom exporté de Blockbench.
     */
    public static LayerDefinition createBodyLayer() {
    MeshDefinition meshDefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0f);
    return LayerDefinition.create(meshDefinition, 64, 64);
    }
}
