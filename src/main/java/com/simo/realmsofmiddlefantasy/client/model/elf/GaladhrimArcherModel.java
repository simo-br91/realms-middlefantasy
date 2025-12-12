package com.simo.realmsofmiddlefantasy.client.model.elf;

import com.simo.realmsofmiddlefantasy.client.model.BaseGeoModel;
import com.simo.realmsofmiddlefantasy.entity.elf.GaladhrimArcherEntity;

public class GaladhrimArcherModel extends BaseGeoModel<GaladhrimArcherEntity> {
    @Override
    protected String modelPath(GaladhrimArcherEntity entity) {
        return "geo/galadhrim_archer.geo.json";
    }

    @Override
    protected String texturePath(GaladhrimArcherEntity entity) {
        return "textures/entity/galadhrim_archer.png";
    }

    @Override
    protected String animationPath(GaladhrimArcherEntity entity) {
        return "animations/galadhrim_archer.animation.json";
    }
}