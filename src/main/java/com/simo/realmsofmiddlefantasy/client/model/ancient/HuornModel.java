package com.simo.realmsofmiddlefantasy.client.model.ancient;

import com.simo.realmsofmiddlefantasy.client.model.BaseGeoModel;
import com.simo.realmsofmiddlefantasy.entity.ancient.HuornEntity;

/**
 * GeckoLib model for Huorn.
 */
public class HuornModel extends BaseGeoModel<HuornEntity> {

    @Override
    protected String modelPath() {
        return "geo/huorn.geo.json";
    }

    @Override
    protected String texturePath() {
        return "textures/entity/huorn.png";
    }

    @Override
    protected String animationPath() {
        return "animations/huorn.animation.json";
    }
}
