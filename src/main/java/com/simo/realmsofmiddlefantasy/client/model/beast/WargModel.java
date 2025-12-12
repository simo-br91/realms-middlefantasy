package com.simo.realmsofmiddlefantasy.client.model.beast;

import com.simo.realmsofmiddlefantasy.client.model.BaseGeoModel;
import com.simo.realmsofmiddlefantasy.entity.beast.WargEntity;

/**
 * GeckoLib model for Warg.
 */
public class WargModel extends BaseGeoModel<WargEntity> {

    @Override
    protected String modelPath() {
        return "geo/warg.geo.json";
    }

    @Override
    protected String texturePath() {
        return "textures/entity/warg.png";
    }

    @Override
    protected String animationPath() {
        return "animations/warg.animation.json";
    }
}
