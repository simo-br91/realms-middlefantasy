package com.simo.realmsofmiddlefantasy.client.render;

import com.simo.realmsofmiddlefantasy.RealmsOfMiddleFantasy;
import com.simo.realmsofmiddlefantasy.entity.custom.DwarfEntity;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.ResourceLocation;

public class DwarfRenderer extends HumanoidMobRenderer<DwarfEntity, HumanoidModel<DwarfEntity>> {

    public static final ModelLayerLocation DWARF_LAYER =
            new ModelLayerLocation(
                    new ResourceLocation(RealmsOfMiddleFantasy.MODID, "dwarf"),
                    "main"
            );

    private static final ResourceLocation DWARF_TEXTURE =
            new ResourceLocation(RealmsOfMiddleFantasy.MODID, "textures/entity/dwarf.png");

    public DwarfRenderer(EntityRendererProvider.Context context) {
        super(context, new HumanoidModel<>(context.bakeLayer(DWARF_LAYER)), 0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(DwarfEntity entity) {
        return DWARF_TEXTURE;
    }

    /**
     * Mesh humanoïde simple pour le nain.
     * (Tu pourras plus tard remplacer ça par une mesh plus trapue si tu veux.)
     */
    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshDefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0f);
        return LayerDefinition.create(meshDefinition, 64, 64);
    }
}
