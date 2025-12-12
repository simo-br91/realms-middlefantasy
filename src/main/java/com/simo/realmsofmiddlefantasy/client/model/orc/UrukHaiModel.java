package com.simo.realmsofmiddlefantasy.client.model.orc;

import com.simo.realmsofmiddlefantasy.client.model.BaseGeoModel;
import com.simo.realmsofmiddlefantasy.entity.orc.UrukHaiEntity;

public class UrukHaiModel extends BaseGeoModel<UrukHaiEntity> {
    @Override
    protected String modelPath(UrukHaiEntity entity) {
        return "geo/uruk_hai.geo.json";
    }

    @Override
    protected String texturePath(UrukHaiEntity entity) {
        return "textures/entity/uruk_hai.png";
    }

    @Override
    protected String animationPath(UrukHaiEntity entity) {
        return "animations/uruk_hai.animation.json";
    }
}