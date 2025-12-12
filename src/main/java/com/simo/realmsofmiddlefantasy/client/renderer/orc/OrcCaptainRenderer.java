package com.simo.realmsofmiddlefantasy.client.renderer.orc;

import com.simo.realmsofmiddlefantasy.client.model.orc.OrcCaptainModel;
import com.simo.realmsofmiddlefantasy.entity.orc.OrcCaptainEntity;
import com.simo.realmsofmiddlefantasy.client.renderer.BaseGeoRenderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;

/**
 * Renderer for Orc Captain.
 */
public class OrcCaptainRenderer extends BaseGeoRenderer<OrcCaptainEntity> {

    private static final float SHADOW_RADIUS = 0.6F;

    public OrcCaptainRenderer(EntityRendererProvider.Context context) {
        super(context, new OrcCaptainModel(), SHADOW_RADIUS);
    }
}
