package com.simo.realmsofmiddlefantasy.client.renderer.goblin;

import com.simo.realmsofmiddlefantasy.client.model.goblin.GoblinModel;
import com.simo.realmsofmiddlefantasy.entity.goblin.GoblinEntity;
import com.simo.realmsofmiddlefantasy.client.renderer.BaseGeoRenderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;

/**
 * Renderer for Goblin.
 */
public class GoblinRenderer extends BaseGeoRenderer<GoblinEntity> {

    private static final float SHADOW_RADIUS = 0.3F;

    public GoblinRenderer(EntityRendererProvider.Context context) {
        super(context, new GoblinModel(), SHADOW_RADIUS);
    }
}
