package com.simo.realmsofmiddlefantasy.client.model.men;

import com.simo.realmsofmiddlefantasy.client.model.BaseGeoModel;
import com.simo.realmsofmiddlefantasy.entity.men.SoldierOfGondorEntity;

public class SoldierOfGondorModel extends BaseGeoModel<SoldierOfGondorEntity> {
    @Override
    protected String modelPath(SoldierOfGondorEntity entity) {
        return "geo/soldier_of_gondor.geo.json";
    }

    @Override
    protected String texturePath(SoldierOfGondorEntity entity) {
        return "textures/entity/soldier_of_gondor.png";
    }

    @Override
    protected String animationPath(SoldierOfGondorEntity entity) {
        return "animations/soldier_of_gondor.animation.json";
    }
}