package com.simo.realmsofmiddlefantasy.client.model;

import com.simo.realmsofmiddlefantasy.RealmsOfMiddleFantasy;
import com.simo.realmsofmiddlefantasy.entity.custom.OrcCaptainEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class OrcCaptainModel extends GeoModel<OrcCaptainEntity> {
    
    @Override
    public ResourceLocation getModelResource(OrcCaptainEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "geo/orc_captain.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(OrcCaptainEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "textures/entity/orc_captain.png");
    }

    @Override
    public ResourceLocation getAnimationResource(OrcCaptainEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "animations/orc_captain.animation.json");
    }
}