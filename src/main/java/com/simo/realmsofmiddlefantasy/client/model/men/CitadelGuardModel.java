package com.simo.realmsofmiddlefantasy.client.model.men;

import com.simo.realmsofmiddlefantasy.client.model.BaseGeoModel;
import com.simo.realmsofmiddlefantasy.entity.men.CitadelGuardEntity;

public class CitadelGuardModel extends BaseGeoModel<CitadelGuardEntity> {
    @Override
    protected String modelPath(CitadelGuardEntity entity) {
        return "geo/citadel_guard.geo.json";
    }

    @Override
    protected String texturePath(CitadelGuardEntity entity) {
        return "textures/entity/citadel_guard.png";
    }

    @Override
    protected String animationPath(CitadelGuardEntity entity) {
        return "animations/citadel_guard.animation.json";
    }
}