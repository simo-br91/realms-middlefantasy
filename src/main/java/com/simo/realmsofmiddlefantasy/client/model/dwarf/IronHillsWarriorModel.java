package com.simo.realmsofmiddlefantasy.client.model.dwarf;

import com.simo.realmsofmiddlefantasy.client.model.BaseGeoModel;
import com.simo.realmsofmiddlefantasy.entity.dwarf.IronHillsWarriorEntity;

public class IronHillsWarriorModel extends BaseGeoModel<IronHillsWarriorEntity> {
    @Override
    protected String modelPath(IronHillsWarriorEntity entity) {
        return "geo/iron_hills_warrior.geo.json";
    }

    @Override
    protected String texturePath(IronHillsWarriorEntity entity) {
        return "textures/entity/iron_hills_warrior.png";
    }

    @Override
    protected String animationPath(IronHillsWarriorEntity entity) {
        return "animations/iron_hills_warrior.animation.json";
    }
}