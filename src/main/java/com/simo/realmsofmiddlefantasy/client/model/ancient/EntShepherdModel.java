package com.simo.realmsofmiddlefantasy.client.model.ancient;

import com.simo.realmsofmiddlefantasy.client.model.BaseGeoModel;
import com.simo.realmsofmiddlefantasy.entity.ancient.EntShepherdEntity;

public class EntShepherdModel extends BaseGeoModel<EntShepherdEntity> {
    @Override
    protected String modelPath(EntShepherdEntity entity) {
        return "geo/ent.geo.json";
    }

    @Override
    protected String texturePath(EntShepherdEntity entity) {
        return "textures/entity/ent.png";
    }

    @Override
    protected String animationPath(EntShepherdEntity entity) {
        return "animations/ent.animation.json";
    }
}