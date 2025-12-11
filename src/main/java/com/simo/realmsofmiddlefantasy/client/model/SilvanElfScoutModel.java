package com.simo.realmsofmiddlefantasy.client.model;

import com.simo.realmsofmiddlefantasy.RealmsOfMiddleFantasy;
import com.simo.realmsofmiddlefantasy.entity.custom.SilvanElfScoutEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SilvanElfScoutModel extends GeoModel<SilvanElfScoutEntity> {
    
    @Override
    public ResourceLocation getModelResource(SilvanElfScoutEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "geo/elf_scout.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SilvanElfScoutEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "textures/entity/elf_scout.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SilvanElfScoutEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "animations/elf_scout.animation.json");
    }
}