package com.simo.realmsofmiddlefantasy.client.model.men;

import com.simo.realmsofmiddlefantasy.client.model.BaseGeoModel;
import com.simo.realmsofmiddlefantasy.entity.men.DunedainRangerEntity;

public class DunedainRangerModel extends BaseGeoModel<DunedainRangerEntity> {
    @Override
    protected String modelPath(DunedainRangerEntity entity) {
        return "geo/dunedain_ranger.geo.json";
    }

    @Override
    protected String texturePath(DunedainRangerEntity entity) {
        return "textures/entity/dunedain_ranger.png";
    }

    @Override
    protected String animationPath(DunedainRangerEntity entity) {
        return "animations/dunedain_ranger.animation.json";
    }
}