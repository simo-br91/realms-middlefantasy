package com.simo.realmsofmiddlefantasy.client.model.dwarf;

import com.simo.realmsofmiddlefantasy.client.model.BaseGeoModel;
import com.simo.realmsofmiddlefantasy.entity.dwarf.DwarfEntity;

public class DwarfModel extends BaseGeoModel<DwarfEntity> {
    @Override
    protected String modelPath(DwarfEntity entity) {
        return "geo/dwarf.geo.json";
    }

    @Override
    protected String texturePath(DwarfEntity entity) {
        return "textures/entity/dwarf.png";
    }

    @Override
    protected String animationPath(DwarfEntity entity) {
        return "animations/dwarf.animation.json";
    }
}