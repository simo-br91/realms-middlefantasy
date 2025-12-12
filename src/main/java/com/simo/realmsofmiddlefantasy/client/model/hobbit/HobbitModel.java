package com.simo.realmsofmiddlefantasy.client.model.hobbit;

import com.simo.realmsofmiddlefantasy.client.model.BaseGeoModel;
import com.simo.realmsofmiddlefantasy.entity.hobbit.HobbitEntity;

public class HobbitModel extends BaseGeoModel<HobbitEntity> {
    @Override
    protected String modelPath(HobbitEntity entity) {
        return "geo/shire_hobbit.geo.json";
    }

    @Override
    protected String texturePath(HobbitEntity entity) {
        return "textures/entity/shire_hobbit.png";
    }

    @Override
    protected String animationPath(HobbitEntity entity) {
        return "animations/shire_hobbit.animation.json";
    }
}