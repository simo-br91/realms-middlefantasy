package com.simo.realmsofmiddlefantasy.client.renderer.undead;

import com.simo.realmsofmiddlefantasy.client.model.undead.WightModel;
import com.simo.realmsofmiddlefantasy.entity.undead.WightEntity;
import com.simo.realmsofmiddlefantasy.client.renderer.BaseGeoRenderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;

/**
 * Renderer for Wight.
 */
public class WightRenderer extends BaseGeoRenderer<WightEntity> {

    private static final float SHADOW_RADIUS = 0.5F;

    public WightRenderer(EntityRendererProvider.Context context) {
        super(context, new WightModel(), SHADOW_RADIUS);
    }
}
