package com.simo.realmsofmiddlefantasy.client.renderer.orc;

import com.simo.realmsofmiddlefantasy.client.model.orc.OrcScoutModel;
import com.simo.realmsofmiddlefantasy.entity.orc.OrcScoutEntity;
import com.simo.realmsofmiddlefantasy.client.renderer.BaseGeoRenderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;

/**
 * Renderer for Orc Scout.
 */
public class OrcScoutRenderer extends BaseGeoRenderer<OrcScoutEntity> {

    private static final float SHADOW_RADIUS = 0.5F;

    public OrcScoutRenderer(EntityRendererProvider.Context context) {
        super(context, new OrcScoutModel(), SHADOW_RADIUS);
    }
}
