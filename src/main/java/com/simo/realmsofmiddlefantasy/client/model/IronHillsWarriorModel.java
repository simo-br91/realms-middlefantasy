package com.simo.realmsofmiddlefantasy.client.model;

import com.simo.realmsofmiddlefantasy.RealmsOfMiddleFantasy;
import com.simo.realmsofmiddlefantasy.entity.custom.IronHillsWarriorEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class IronHillsWarriorModel extends GeoModel<IronHillsWarriorEntity> {
    
    @Override
    public ResourceLocation getModelResource(IronHillsWarriorEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "geo/iron_hills_warrior.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(IronHillsWarriorEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "textures/entity/iron_hills_warrior.png");
    }

    @Override
    public ResourceLocation getAnimationResource(IronHillsWarriorEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "animations/iron_hills_warrior_animation.json");
    }
}