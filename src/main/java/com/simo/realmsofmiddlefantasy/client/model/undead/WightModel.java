package com.simo.realmsofmiddlefantasy.client.model.undead;

import com.simo.realmsofmiddlefantasy.client.model.BaseGeoModel;
import com.simo.realmsofmiddlefantasy.entity.undead.WightEntity;

public class WightModel extends BaseGeoModel<WightEntity> {
    @Override
    protected String modelPath(WightEntity entity) {
        return "geo/wight.geo.json";
    }

    @Override
    protected String texturePath(WightEntity entity) {
        return "textures/entity/wight.png";
    }

    @Override
    protected String animationPath(WightEntity entity) {
        return "animations/wight.animation.json";
    }
}