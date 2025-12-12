package com.simo.realmsofmiddlefantasy.client.model.beast;

import com.simo.realmsofmiddlefantasy.client.model.BaseGeoModel;
import com.simo.realmsofmiddlefantasy.entity.beast.GreatEagleEntity;

/**
 * GeckoLib model for Great Eagle.
 */
public class GreatEagleModel extends BaseGeoModel<GreatEagleEntity> {

    @Override
    protected String modelPath() {
        return "geo/great_eagle.geo.json";
    }

    @Override
    protected String texturePath() {
        return "textures/entity/great_eagle.png";
    }

    @Override
    protected String animationPath() {
        return "animations/great_eagle.animation.json";
    }
}
