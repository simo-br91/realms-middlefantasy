package com.simo.realmsofmiddlefantasy.client.model.orc;

import com.simo.realmsofmiddlefantasy.client.model.BaseGeoModel;
import com.simo.realmsofmiddlefantasy.entity.orc.OrcCaptainEntity;

public class OrcCaptainModel extends BaseGeoModel<OrcCaptainEntity> {
    @Override
    protected String modelPath(OrcCaptainEntity entity) {
        return "geo/orc_captain.geo.json";
    }

    @Override
    protected String texturePath(OrcCaptainEntity entity) {
        return "textures/entity/orc_captain.png";
    }

    @Override
    protected String animationPath(OrcCaptainEntity entity) {
        return "animations/orc_captain.animation.json";
    }
}