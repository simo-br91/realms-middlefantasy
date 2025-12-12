package com.simo.realmsofmiddlefantasy.client.model.ancient;

import com.simo.realmsofmiddlefantasy.client.model.BaseGeoModel;
import com.simo.realmsofmiddlefantasy.entity.ancient.HuornEntity;

public class HuornModel extends BaseGeoModel<HuornEntity> {
    @Override
    protected String modelPath(HuornEntity entity) {
        return "geo/huorn.geo.json";
    }

    @Override
    protected String texturePath(HuornEntity entity) {
        return "textures/entity/huorn_texture.png";
    }

    @Override
    protected String animationPath(HuornEntity entity) {
        return "animations/huorn.animation.json";
    }
}