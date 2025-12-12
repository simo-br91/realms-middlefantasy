package com.simo.realmsofmiddlefantasy.client.model.ancient;

import com.simo.realmsofmiddlefantasy.client.model.BaseGeoModel;
import com.simo.realmsofmiddlefantasy.entity.ancient.EntShepherdEntity;

/**
 * GeckoLib model for Ent Shepherd.
 */
public class EntShepherdModel extends BaseGeoModel<EntShepherdEntity> {

    @Override
    protected String modelPath() {
        return "geo/ent.geo.json";
    }

    @Override
    protected String texturePath() {
        return "textures/entity/ent.png";
    }

    @Override
    protected String animationPath() {
        return "animations/ent.animation.json";
    }
}
