package com.simo.realmsofmiddlefantasy.client.model;

import com.simo.realmsofmiddlefantasy.RealmsOfMiddleFantasy;
import com.simo.realmsofmiddlefantasy.entity.custom.EntShepherdEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EntShepherdModel extends GeoModel<EntShepherdEntity> {
    
    @Override
    public ResourceLocation getModelResource(EntShepherdEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "geo/ent.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EntShepherdEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "textures/entity/ent.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EntShepherdEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "animations/ent.animation.json");
    }
}