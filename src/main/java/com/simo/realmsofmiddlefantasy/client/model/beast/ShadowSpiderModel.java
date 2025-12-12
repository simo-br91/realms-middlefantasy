package com.simo.realmsofmiddlefantasy.client.model.beast;

import com.simo.realmsofmiddlefantasy.client.model.BaseGeoModel;
import com.simo.realmsofmiddlefantasy.entity.beast.ShadowSpiderEntity;

/**
 * GeckoLib model for Shadow Spider.
 */
public class ShadowSpiderModel extends BaseGeoModel<ShadowSpiderEntity> {

    @Override
    protected String modelPath() {
        return "geo/shadow_spider.geo.json";
    }

    @Override
    protected String texturePath() {
        return "textures/entity/shadow_spider.png";
    }

    @Override
    protected String animationPath() {
        return "animations/shadow_spider.animation.json";
    }
}
