package com.simo.realmsofmiddlefantasy.client.model.men;

import com.simo.realmsofmiddlefantasy.RealmsOfMiddleFantasy;
import com.simo.realmsofmiddlefantasy.entity.men.DunedainRangerEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class DunedainRangerModel extends GeoModel<DunedainRangerEntity> {
    
    @Override
    public ResourceLocation getModelResource(DunedainRangerEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "geo/dunedain_ranger.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(DunedainRangerEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "textures/entity/dunedain_ranger.png");
    }

    @Override
    public ResourceLocation getAnimationResource(DunedainRangerEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "animations/dunedain_ranger.animation.json");
    }
}