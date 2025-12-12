package com.simo.realmsofmiddlefantasy.client.model.orc;

import com.simo.realmsofmiddlefantasy.RealmsOfMiddleFantasy;
import com.simo.realmsofmiddlefantasy.entity.orc.OrcWarriorEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class OrcWarriorModel extends GeoModel<OrcWarriorEntity> {
    
    @Override
    public ResourceLocation getModelResource(OrcWarriorEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "geo/orc_warrior.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(OrcWarriorEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "textures/entity/orc_warrior.png");
    }

    @Override
    public ResourceLocation getAnimationResource(OrcWarriorEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "animations/orc_warrior.animation.json");
    }
}