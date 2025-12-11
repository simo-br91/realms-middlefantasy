package com.simo.realmsofmiddlefantasy.client.model;

import com.simo.realmsofmiddlefantasy.RealmsOfMiddleFantasy;
import com.simo.realmsofmiddlefantasy.entity.custom.HighElfWarriorEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class HighElfWarriorModel extends GeoModel<HighElfWarriorEntity> {
    
    @Override
    public ResourceLocation getModelResource(HighElfWarriorEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "geo/high_elf_warrior.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(HighElfWarriorEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "textures/entity/high_elf_warrior.png");
    }

    @Override
    public ResourceLocation getAnimationResource(HighElfWarriorEntity entity) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, "animations/high_elf_warrior.animation.json");
    }
}