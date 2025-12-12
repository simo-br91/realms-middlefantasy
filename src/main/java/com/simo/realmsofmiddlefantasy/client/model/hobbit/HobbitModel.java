package com.simo.realmsofmiddlefantasy.client.model.hobbit;

import com.simo.realmsofmiddlefantasy.RealmsOfMiddleFantasy;
import com.simo.realmsofmiddlefantasy.entity.hobbit.HobbitEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class HobbitModel extends GeoModel<HobbitEntity> {
    
    @Override
    public ResourceLocation getModelResource(HobbitEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "geo/shire_hobbit.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(HobbitEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "textures/entity/shire_hobbit.png");
    }

    @Override
    public ResourceLocation getAnimationResource(HobbitEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "animations/shire_hobbit.animation.json");
    }
}