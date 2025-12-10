package com.simo.realmsofmiddlefantasy.client.model;

import com.simo.realmsofmiddlefantasy.RealmsOfMiddleFantasy;
import com.simo.realmsofmiddlefantasy.entity.custom.OrcEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class OrcModel extends GeoModel<OrcEntity> {
    
    @Override
    public ResourceLocation getModelResource(OrcEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "geo/orc.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(OrcEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "textures/entity/orc.png");
    }

    @Override
    public ResourceLocation getAnimationResource(OrcEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "animations/orc.animation.json");
    }
}