package com.simo.realmsofmiddlefantasy.client.model;

import com.simo.realmsofmiddlefantasy.RealmsOfMiddleFantasy;
import com.simo.realmsofmiddlefantasy.entity.custom.CaveTrollEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class CaveTrollModel extends GeoModel<CaveTrollEntity> {
    
    @Override
    public ResourceLocation getModelResource(CaveTrollEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "geo/cave_troll.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CaveTrollEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "textures/entity/cave_troll.png");
    }

    @Override
    public ResourceLocation getAnimationResource(CaveTrollEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "animations/cave_troll.animation.json");
    }
}