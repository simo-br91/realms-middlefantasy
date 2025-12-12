package com.simo.realmsofmiddlefantasy.client.model.orc;

import com.simo.realmsofmiddlefantasy.RealmsOfMiddleFantasy;
import com.simo.realmsofmiddlefantasy.entity.orc.OrcScoutEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class OrcScoutModel extends GeoModel<OrcScoutEntity> {
    
    @Override
    public ResourceLocation getModelResource(OrcScoutEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "geo/orc_scout.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(OrcScoutEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "textures/entity/orc_scout.png");
    }

    @Override
    public ResourceLocation getAnimationResource(OrcScoutEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "animations/orc_scout.animation.json");
    }
}