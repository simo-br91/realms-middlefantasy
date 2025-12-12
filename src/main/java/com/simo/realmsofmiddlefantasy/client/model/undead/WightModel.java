package com.simo.realmsofmiddlefantasy.client.model.undead;

import com.simo.realmsofmiddlefantasy.RealmsOfMiddleFantasy;
import com.simo.realmsofmiddlefantasy.entity.undead.WightEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class WightModel extends GeoModel<WightEntity> {
    
    @Override
    public ResourceLocation getModelResource(WightEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "geo/wight.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(WightEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "textures/entity/wight.png");
    }

    @Override
    public ResourceLocation getAnimationResource(WightEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "animations/wight.animation.json");
    }
}