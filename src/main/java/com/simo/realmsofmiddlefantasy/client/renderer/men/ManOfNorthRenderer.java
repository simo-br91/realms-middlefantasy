package com.simo.realmsofmiddlefantasy.client.renderer.men;

import com.simo.realmsofmiddlefantasy.client.model.men.ManOfNorthModel;
import com.simo.realmsofmiddlefantasy.entity.men.ManOfNorthEntity;
import com.simo.realmsofmiddlefantasy.client.renderer.BaseGeoRenderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;

/**
 * Renderer for Man of North.
 */
public class ManOfNorthRenderer extends BaseGeoRenderer<ManOfNorthEntity> {

    private static final float SHADOW_RADIUS = 0.5F;

    public ManOfNorthRenderer(EntityRendererProvider.Context context) {
        super(context, new ManOfNorthModel(), SHADOW_RADIUS);
    }
}
