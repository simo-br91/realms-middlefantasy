package com.simo.realmsofmiddlefantasy.client.model;

import com.simo.realmsofmiddlefantasy.RealmsOfMiddleFantasy;
import com.simo.realmsofmiddlefantasy.entity.custom.TrollEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class TrollModel extends GeoModel<TrollEntity> {
    
    @Override
    public ResourceLocation getModelResource(TrollEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "geo/troll.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(TrollEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "textures/entity/troll.png");
    }

    @Override
    public ResourceLocation getAnimationResource(TrollEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "animations/troll.animation.json");
    }
}