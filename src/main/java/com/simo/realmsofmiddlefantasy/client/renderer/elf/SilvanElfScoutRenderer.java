package com.simo.realmsofmiddlefantasy.client.renderer.elf;

import com.simo.realmsofmiddlefantasy.client.model.elf.SilvanElfScoutModel;
import com.simo.realmsofmiddlefantasy.entity.elf.SilvanElfScoutEntity;
import com.simo.realmsofmiddlefantasy.client.renderer.BaseGeoRenderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;

/**
 * Renderer for Silvan Elf Scout.
 */
public class SilvanElfScoutRenderer extends BaseGeoRenderer<SilvanElfScoutEntity> {

    private static final float SHADOW_RADIUS = 0.5F;

    public SilvanElfScoutRenderer(EntityRendererProvider.Context context) {
        super(context, new SilvanElfScoutModel(), SHADOW_RADIUS);
    }
}
