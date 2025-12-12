package com.simo.realmsofmiddlefantasy.client.model.orc;

import com.simo.realmsofmiddlefantasy.client.model.BaseGeoModel;
import com.simo.realmsofmiddlefantasy.entity.orc.OrcWarriorEntity;

public class OrcWarriorModel extends BaseGeoModel<OrcWarriorEntity> {
    @Override
    protected String modelPath(OrcWarriorEntity entity) {
        return "geo/orc_warrior.geo.json";
    }

    @Override
    protected String texturePath(OrcWarriorEntity entity) {
        return "textures/entity/orc_warrior.png";
    }

    @Override
    protected String animationPath(OrcWarriorEntity entity) {
        return "animations/orc_warrior.animation.json";
    }
}