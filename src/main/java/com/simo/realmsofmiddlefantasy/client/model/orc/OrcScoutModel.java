package com.simo.realmsofmiddlefantasy.client.model.orc;

import com.simo.realmsofmiddlefantasy.client.model.BaseGeoModel;
import com.simo.realmsofmiddlefantasy.entity.orc.OrcScoutEntity;

public class OrcScoutModel extends BaseGeoModel<OrcScoutEntity> {
    @Override
    protected String modelPath(OrcScoutEntity entity) {
        return "geo/orc_scout.geo.json";
    }

    @Override
    protected String texturePath(OrcScoutEntity entity) {
        return "textures/entity/orc_scout.png";
    }

    @Override
    protected String animationPath(OrcScoutEntity entity) {
        return "animations/orc_scout.animation.json";
    }
}