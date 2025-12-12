package com.simo.realmsofmiddlefantasy.client.model.beast;

import com.simo.realmsofmiddlefantasy.client.model.BaseGeoModel;
import com.simo.realmsofmiddlefantasy.entity.beast.GreatEagleEntity;

public class GreatEagleModel extends BaseGeoModel<GreatEagleEntity> {
    @Override
    protected String modelPath(GreatEagleEntity entity) {
        return "geo/great_eagle.geo.json";
    }

    @Override
    protected String texturePath(GreatEagleEntity entity) {
        return "textures/entity/great_eagle.png";
    }

    @Override
    protected String animationPath(GreatEagleEntity entity) {
        return "animations/great_eagle.animation.json";
    }
}