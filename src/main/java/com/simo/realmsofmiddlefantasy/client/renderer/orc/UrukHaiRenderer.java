package com.simo.realmsofmiddlefantasy.client.renderer.orc;

import com.simo.realmsofmiddlefantasy.client.model.orc.UrukHaiModel;
import com.simo.realmsofmiddlefantasy.entity.orc.UrukHaiEntity;
import com.simo.realmsofmiddlefantasy.client.renderer.BaseGeoRenderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;

/**
 * Renderer for Uruk-Hai.
 */
public class UrukHaiRenderer extends BaseGeoRenderer<UrukHaiEntity> {

    private static final float SHADOW_RADIUS = 0.6F;

    public UrukHaiRenderer(EntityRendererProvider.Context context) {
        super(context, new UrukHaiModel(), SHADOW_RADIUS);
    }
}
