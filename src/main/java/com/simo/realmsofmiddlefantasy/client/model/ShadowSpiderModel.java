package com.simo.realmsofmiddlefantasy.client.model;

import com.simo.realmsofmiddlefantasy.RealmsOfMiddleFantasy;
import com.simo.realmsofmiddlefantasy.entity.custom.ShadowSpiderEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ShadowSpiderModel extends GeoModel<ShadowSpiderEntity> {
    
    @Override
    public ResourceLocation getModelResource(ShadowSpiderEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "geo/shadow_spider.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ShadowSpiderEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "textures/entity/shadow_spider.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ShadowSpiderEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "animations/shadow_spider.animation.json");
    }
}