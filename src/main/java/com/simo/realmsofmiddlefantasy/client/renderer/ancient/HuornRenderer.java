package com.simo.realmsofmiddlefantasy.client.renderer.ancient;

import com.simo.realmsofmiddlefantasy.client.model.ancient.HuornModel;
import com.simo.realmsofmiddlefantasy.client.renderer.BaseGeoRenderer;
import com.simo.realmsofmiddlefantasy.entity.ancient.HuornEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;

/**
 * Renderer for Huorn.
 */
public class HuornRenderer extends BaseGeoRenderer<HuornEntity> {

    private static final float SHADOW_RADIUS = 1.2F;

    public HuornRenderer(EntityRendererProvider.Context context) {
        super(context, new HuornModel(), SHADOW_RADIUS);
    }
}
