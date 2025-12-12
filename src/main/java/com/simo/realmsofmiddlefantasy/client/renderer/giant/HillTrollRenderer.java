package com.simo.realmsofmiddlefantasy.client.renderer.giant;

import com.simo.realmsofmiddlefantasy.client.model.giant.HillTrollModel;
import com.simo.realmsofmiddlefantasy.entity.giant.HillTrollEntity;
import com.simo.realmsofmiddlefantasy.client.renderer.BaseGeoRenderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;

/**
 * Renderer for Hill Troll.
 */
public class HillTrollRenderer extends BaseGeoRenderer<HillTrollEntity> {

    private static final float SHADOW_RADIUS = 1.1F;

    public HillTrollRenderer(EntityRendererProvider.Context context) {
        super(context, new HillTrollModel(), SHADOW_RADIUS);
    }
}
