package com.simo.realmsofmiddlefantasy.client.model.giant;

import com.simo.realmsofmiddlefantasy.client.model.BaseGeoModel;
import com.simo.realmsofmiddlefantasy.entity.giant.HillTrollEntity;

public class HillTrollModel extends BaseGeoModel<HillTrollEntity> {
    @Override
    protected String modelPath(HillTrollEntity entity) {
        return "geo/hill_troll.geo.json";
    }

    @Override
    protected String texturePath(HillTrollEntity entity) {
        return "textures/entity/hill_troll.png";
    }

    @Override
    protected String animationPath(HillTrollEntity entity) {
        return "animations/hill_troll.animation.json";
    }
}