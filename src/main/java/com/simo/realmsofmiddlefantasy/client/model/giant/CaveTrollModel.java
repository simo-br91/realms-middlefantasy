package com.simo.realmsofmiddlefantasy.client.model.giant;

import com.simo.realmsofmiddlefantasy.client.model.BaseGeoModel;
import com.simo.realmsofmiddlefantasy.entity.giant.CaveTrollEntity;

public class CaveTrollModel extends BaseGeoModel<CaveTrollEntity> {
    @Override
    protected String modelPath(CaveTrollEntity entity) {
        return "geo/cave_troll.geo.json";
    }

    @Override
    protected String texturePath(CaveTrollEntity entity) {
        return "textures/entity/cave_troll.png";
    }

    @Override
    protected String animationPath(CaveTrollEntity entity) {
        return "animations/cave_troll.animation.json";
    }
}