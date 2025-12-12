package com.simo.realmsofmiddlefantasy.client.model.orc;

import com.simo.realmsofmiddlefantasy.client.model.BaseGeoModel;
import com.simo.realmsofmiddlefantasy.entity.orc.OrcEntity;

public class OrcModel extends BaseGeoModel<OrcEntity> {
    @Override
    protected String modelPath(OrcEntity entity) {
        return "geo/orc.geo.json";
    }

    @Override
    protected String texturePath(OrcEntity entity) {
        return "textures/entity/orc.png";
    }

    @Override
    protected String animationPath(OrcEntity entity) {
        return "animations/orc.animation.json";
    }
}