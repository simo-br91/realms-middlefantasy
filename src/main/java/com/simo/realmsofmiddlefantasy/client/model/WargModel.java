package com.simo.realmsofmiddlefantasy.client.model;

import com.simo.realmsofmiddlefantasy.RealmsOfMiddleFantasy;
import com.simo.realmsofmiddlefantasy.entity.custom.WargEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class WargModel extends GeoModel<WargEntity> {
    
    @Override
    public ResourceLocation getModelResource(WargEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "geo/warg.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(WargEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "textures/entity/warg.png");
    }

    @Override
    public ResourceLocation getAnimationResource(WargEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "animations/warg.animation.json");
    }
}