package com.simo.realmsofmiddlefantasy.client.model.beast;

import com.simo.realmsofmiddlefantasy.client.model.BaseGeoModel;
import com.simo.realmsofmiddlefantasy.entity.beast.WargEntity;

public class WargModel extends BaseGeoModel<WargEntity> {
    @Override
    protected String modelPath(WargEntity entity) {
        return "geo/warg.geo.json";
    }

    @Override
    protected String texturePath(WargEntity entity) {
        return "textures/entity/warg.png";
    }

    @Override
    protected String animationPath(WargEntity entity) {
        return "animations/warg.animation.json";
    }
}