package com.simo.realmsofmiddlefantasy.client.model;

import com.simo.realmsofmiddlefantasy.RealmsOfMiddleFantasy;
import com.simo.realmsofmiddlefantasy.entity.custom.GaladhrimArcherEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class GaladhrimArcherModel extends GeoModel<GaladhrimArcherEntity> {
    
    @Override
    public ResourceLocation getModelResource(GaladhrimArcherEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "geo/galadhrim_archer.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GaladhrimArcherEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "textures/entity/galadhrim_archer.png");
    }

    @Override
    public ResourceLocation getAnimationResource(GaladhrimArcherEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "animations/galadhrim_archer.animation.json");
    }
}