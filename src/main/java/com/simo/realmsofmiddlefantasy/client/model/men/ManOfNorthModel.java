package com.simo.realmsofmiddlefantasy.client.model.men;

import com.simo.realmsofmiddlefantasy.RealmsOfMiddleFantasy;
import com.simo.realmsofmiddlefantasy.entity.men.ManOfNorthEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ManOfNorthModel extends GeoModel<ManOfNorthEntity> {
    
    @Override
    public ResourceLocation getModelResource(ManOfNorthEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "geo/man_of_north.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ManOfNorthEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "textures/entity/man_of_north.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ManOfNorthEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "animations/man_of_north.animation.json");
    }
}