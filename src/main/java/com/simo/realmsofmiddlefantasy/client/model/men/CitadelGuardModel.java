package com.simo.realmsofmiddlefantasy.client.model.men;

import com.simo.realmsofmiddlefantasy.RealmsOfMiddleFantasy;
import com.simo.realmsofmiddlefantasy.entity.men.CitadelGuardEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class CitadelGuardModel extends GeoModel<CitadelGuardEntity> {
    
    @Override
    public ResourceLocation getModelResource(CitadelGuardEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "geo/citadel_guard.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CitadelGuardEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "textures/entity/citadel_guard.png");
    }

    @Override
    public ResourceLocation getAnimationResource(CitadelGuardEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "animations/citadel_guard.animation.json");
    }
}