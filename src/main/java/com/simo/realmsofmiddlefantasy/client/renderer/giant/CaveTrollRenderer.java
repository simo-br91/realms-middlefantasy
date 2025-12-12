package com.simo.realmsofmiddlefantasy.client.renderer.giant;

import com.simo.realmsofmiddlefantasy.client.model.giant.CaveTrollModel;
import com.simo.realmsofmiddlefantasy.entity.giant.CaveTrollEntity;
import com.simo.realmsofmiddlefantasy.client.renderer.BaseGeoRenderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;

/**
 * Renderer for Cave Troll.
 */
public class CaveTrollRenderer extends BaseGeoRenderer<CaveTrollEntity> {

    private static final float SHADOW_RADIUS = 1.3F;

    public CaveTrollRenderer(EntityRendererProvider.Context context) {
        super(context, new CaveTrollModel(), SHADOW_RADIUS);
    }
}
