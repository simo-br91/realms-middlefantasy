package com.simo.realmsofmiddlefantasy.client.renderer.orc;

import com.simo.realmsofmiddlefantasy.client.model.orc.OrcModel;
import com.simo.realmsofmiddlefantasy.entity.orc.OrcEntity;
import com.simo.realmsofmiddlefantasy.client.renderer.BaseGeoRenderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;

/**
 * Renderer for Orc.
 */
public class OrcRenderer extends BaseGeoRenderer<OrcEntity> {

    private static final float SHADOW_RADIUS = 0.5F;

    public OrcRenderer(EntityRendererProvider.Context context) {
        super(context, new OrcModel(), SHADOW_RADIUS);
    }
}
