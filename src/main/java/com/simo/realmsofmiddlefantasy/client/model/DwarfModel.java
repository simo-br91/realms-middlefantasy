package com.simo.realmsofmiddlefantasy.client.model;

import com.simo.realmsofmiddlefantasy.RealmsOfMiddleFantasy;
import com.simo.realmsofmiddlefantasy.entity.custom.DwarfEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class DwarfModel extends GeoModel<DwarfEntity> {
    
    @Override
    public ResourceLocation getModelResource(DwarfEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "geo/dwarf.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(DwarfEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "textures/entity/dwarf.png");
    }

    @Override
    public ResourceLocation getAnimationResource(DwarfEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "animations/dwarf.animation.json");
    }
}