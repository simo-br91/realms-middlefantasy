package com.simo.realmsofmiddlefantasy.client.model.elf;

import com.simo.realmsofmiddlefantasy.client.model.BaseGeoModel;
import com.simo.realmsofmiddlefantasy.entity.elf.HighElfWarriorEntity;

public class HighElfWarriorModel extends BaseGeoModel<HighElfWarriorEntity> {
    @Override
    protected String modelPath(HighElfWarriorEntity entity) {
        return "geo/high_elf_warrior.geo.json";
    }

    @Override
    protected String texturePath(HighElfWarriorEntity entity) {
        return "textures/entity/high_elf_warrior.png";
    }

    @Override
    protected String animationPath(HighElfWarriorEntity entity) {
        return "animations/high_elf_warrior.animation.json";
    }
}