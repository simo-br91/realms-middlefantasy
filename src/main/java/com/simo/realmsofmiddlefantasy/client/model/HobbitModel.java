package com.simo.realmsofmiddlefantasy.client.model;

import com.simo.realmsofmiddlefantasy.RealmsOfMiddleFantasy;
import com.simo.realmsofmiddlefantasy.entity.custom.HobbitEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class HobbitModel extends GeoModel<HobbitEntity> {
    
    @Override
    public ResourceLocation getModelResource(HobbitEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "geo/shire_hhobbit_geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(HobbitEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "textures/entity/shire_hhobbit.png");
    }

    @Override
    public ResourceLocation getAnimationResource(HobbitEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "animations/shire_hobbit_animation.json");
    }
}