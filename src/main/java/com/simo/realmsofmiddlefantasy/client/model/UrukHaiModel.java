package com.simo.realmsofmiddlefantasy.client.model;

import com.simo.realmsofmiddlefantasy.RealmsOfMiddleFantasy;
import com.simo.realmsofmiddlefantasy.entity.custom.UrukHaiEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class UrukHaiModel extends GeoModel<UrukHaiEntity> {
    
    @Override
    public ResourceLocation getModelResource(UrukHaiEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "geo/uruk_hai.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(UrukHaiEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "textures/entity/uruk_hai.png");
    }

    @Override
    public ResourceLocation getAnimationResource(UrukHaiEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "animations/uruk_hai.animation.json");
    }
}