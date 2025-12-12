package com.simo.realmsofmiddlefantasy.client.model.beast;

import com.simo.realmsofmiddlefantasy.client.model.BaseGeoModel;
import com.simo.realmsofmiddlefantasy.entity.beast.ShadowSpiderEntity;

public class ShadowSpiderModel extends BaseGeoModel<ShadowSpiderEntity> {
    @Override
    protected String modelPath(ShadowSpiderEntity entity) {
        return "geo/shadow_spider.geo.json";
    }

    @Override
    protected String texturePath(ShadowSpiderEntity entity) {
        return "textures/entity/shadow_spider.png";
    }

    @Override
    protected String animationPath(ShadowSpiderEntity entity) {
        return "animations/shadow_spider.animation.json";
    }
}