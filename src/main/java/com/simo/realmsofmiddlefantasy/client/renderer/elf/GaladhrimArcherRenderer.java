package com.simo.realmsofmiddlefantasy.client.renderer.elf;

import com.simo.realmsofmiddlefantasy.client.model.elf.GaladhrimArcherModel;
import com.simo.realmsofmiddlefantasy.entity.elf.GaladhrimArcherEntity;
import com.simo.realmsofmiddlefantasy.client.renderer.BaseGeoRenderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;

/**
 * Renderer for Galadhrim Archer.
 */
public class GaladhrimArcherRenderer extends BaseGeoRenderer<GaladhrimArcherEntity> {

    private static final float SHADOW_RADIUS = 0.5F;

    public GaladhrimArcherRenderer(EntityRendererProvider.Context context) {
        super(context, new GaladhrimArcherModel(), SHADOW_RADIUS);
    }
}
