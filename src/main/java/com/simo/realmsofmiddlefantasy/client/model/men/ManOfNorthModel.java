package com.simo.realmsofmiddlefantasy.client.model.men;

import com.simo.realmsofmiddlefantasy.client.model.BaseGeoModel;
import com.simo.realmsofmiddlefantasy.entity.men.ManOfNorthEntity;

public class ManOfNorthModel extends BaseGeoModel<ManOfNorthEntity> {
    @Override
    protected String modelPath(ManOfNorthEntity entity) {
        return "geo/man_of_north.geo.json";
    }

    @Override
    protected String texturePath(ManOfNorthEntity entity) {
        return "textures/entity/man_of_north.png";
    }

    @Override
    protected String animationPath(ManOfNorthEntity entity) {
        return "animations/man_of_north.animation.json";
    }
}