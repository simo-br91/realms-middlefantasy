package com.simo.realmsofmiddlefantasy.client.model;

import com.simo.realmsofmiddlefantasy.RealmsOfMiddleFantasy;
import com.simo.realmsofmiddlefantasy.entity.custom.GoblinEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class GoblinModel extends GeoModel<GoblinEntity> {
    
    @Override
    public ResourceLocation getModelResource(GoblinEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "geo/goblin.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GoblinEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "textures/entity/goblin.png");
    }

    @Override
    public ResourceLocation getAnimationResource(GoblinEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "animations/goblin.animation.json");
    }
}