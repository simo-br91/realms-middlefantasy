package com.simo.realmsofmiddlefantasy.client.renderer.ancient;

import com.simo.realmsofmiddlefantasy.client.model.ancient.EntShepherdModel;
import com.simo.realmsofmiddlefantasy.client.renderer.BaseGeoRenderer;
import com.simo.realmsofmiddlefantasy.entity.ancient.EntShepherdEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;

/**
 * Renderer for Ent Shepherd.
 */
public class EntShepherdRenderer extends BaseGeoRenderer<EntShepherdEntity> {

    private static final float SHADOW_RADIUS = 1.5F;

    public EntShepherdRenderer(EntityRendererProvider.Context context) {
        super(context, new EntShepherdModel(), SHADOW_RADIUS);
    }
}
