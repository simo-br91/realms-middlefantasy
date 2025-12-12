package com.simo.realmsofmiddlefantasy.client.model.giant;

import com.simo.realmsofmiddlefantasy.RealmsOfMiddleFantasy;
import com.simo.realmsofmiddlefantasy.entity.giant.HillTrollEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class HillTrollModel extends GeoModel<HillTrollEntity> {
    
    @Override
    public ResourceLocation getModelResource(HillTrollEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "geo/hill_troll.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(HillTrollEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "textures/entity/hill_troll.png");
    }

    @Override
    public ResourceLocation getAnimationResource(HillTrollEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "animations/hill_troll.animation.json");
    }
}