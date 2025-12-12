package com.simo.realmsofmiddlefantasy.client.model.elf;

import com.simo.realmsofmiddlefantasy.client.model.BaseGeoModel;
import com.simo.realmsofmiddlefantasy.entity.elf.SilvanElfScoutEntity;

public class SilvanElfScoutModel extends BaseGeoModel<SilvanElfScoutEntity> {
    @Override
    protected String modelPath(SilvanElfScoutEntity entity) {
        return "geo/elf_scout.geo.json";
    }

    @Override
    protected String texturePath(SilvanElfScoutEntity entity) {
        return "textures/entity/elf_scout.png";
    }

    @Override
    protected String animationPath(SilvanElfScoutEntity entity) {
        return "animations/elf_scout.animation.json";
    }
}