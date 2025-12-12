package com.simo.realmsofmiddlefantasy.client.renderer.men;

import com.simo.realmsofmiddlefantasy.client.model.men.DunedainRangerModel;
import com.simo.realmsofmiddlefantasy.entity.men.DunedainRangerEntity;
import com.simo.realmsofmiddlefantasy.client.renderer.BaseGeoRenderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;

/**
 * Renderer for Dunedain Ranger.
 */
public class DunedainRangerRenderer extends BaseGeoRenderer<DunedainRangerEntity> {

    private static final float SHADOW_RADIUS = 0.5F;

    public DunedainRangerRenderer(EntityRendererProvider.Context context) {
        super(context, new DunedainRangerModel(), SHADOW_RADIUS);
    }
}
