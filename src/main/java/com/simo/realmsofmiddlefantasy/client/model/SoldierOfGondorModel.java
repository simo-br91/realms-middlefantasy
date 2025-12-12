package com.simo.realmsofmiddlefantasy.client.model;

import com.simo.realmsofmiddlefantasy.RealmsOfMiddleFantasy;
import com.simo.realmsofmiddlefantasy.entity.custom.SoldierOfGondorEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SoldierOfGondorModel extends GeoModel<SoldierOfGondorEntity> {
    
    @Override
    public ResourceLocation getModelResource(SoldierOfGondorEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "geo/soldier_of_gondor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SoldierOfGondorEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "textures/entity/soldier_of_gondor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SoldierOfGondorEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "animations/soldier_of_gondor.animation.json");
    }
}