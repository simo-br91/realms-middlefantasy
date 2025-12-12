package com.simo.realmsofmiddlefantasy.client.renderer.men;

import com.simo.realmsofmiddlefantasy.client.model.men.CitadelGuardModel;
import com.simo.realmsofmiddlefantasy.entity.men.CitadelGuardEntity;
import com.simo.realmsofmiddlefantasy.client.renderer.BaseGeoRenderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;

/**
 * Renderer for Citadel Guard.
 */
public class CitadelGuardRenderer extends BaseGeoRenderer<CitadelGuardEntity> {

    private static final float SHADOW_RADIUS = 0.5F;

    public CitadelGuardRenderer(EntityRendererProvider.Context context) {
        super(context, new CitadelGuardModel(), SHADOW_RADIUS);
    }
}
