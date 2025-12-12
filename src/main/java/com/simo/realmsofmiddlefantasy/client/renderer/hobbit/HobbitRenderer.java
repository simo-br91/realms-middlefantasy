package com.simo.realmsofmiddlefantasy.client.renderer.hobbit;

import com.simo.realmsofmiddlefantasy.client.model.hobbit.HobbitModel;
import com.simo.realmsofmiddlefantasy.entity.hobbit.HobbitEntity;
import com.simo.realmsofmiddlefantasy.client.renderer.BaseGeoRenderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;

/**
 * Renderer for Hobbit.
 */
public class HobbitRenderer extends BaseGeoRenderer<HobbitEntity> {

    private static final float SHADOW_RADIUS = 0.4F;

    public HobbitRenderer(EntityRendererProvider.Context context) {
        super(context, new HobbitModel(), SHADOW_RADIUS);
    }
}
