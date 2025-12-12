package com.simo.realmsofmiddlefantasy.client.model;

import com.simo.realmsofmiddlefantasy.RealmsOfMiddleFantasy;
import com.simo.realmsofmiddlefantasy.entity.custom.HobbitEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class HobbitModel extends GeoModel<HobbitEntity> {
    
    @Override
    public ResourceLocation getModelResource(HobbitEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "geo/hobbit_geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(HobbitEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "textures/entity/hobbit.png");
    }

    @Override
    public ResourceLocation getAnimationResource(HobbitEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "animations/hobbit_animation.json");
    }
}