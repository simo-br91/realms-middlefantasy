package com.simo.realmsofmiddlefantasy.client.model.goblin;

import com.simo.realmsofmiddlefantasy.client.model.BaseGeoModel;
import com.simo.realmsofmiddlefantasy.entity.goblin.GoblinEntity;

public class GoblinModel extends BaseGeoModel<GoblinEntity> {
    @Override
    protected String modelPath(GoblinEntity entity) {
        return "geo/goblin.geo.json";
    }

    @Override
    protected String texturePath(GoblinEntity entity) {
        return "textures/entity/goblin.png";
    }

    @Override
    protected String animationPath(GoblinEntity entity) {
        return "animations/goblin.animation.json";
    }
}