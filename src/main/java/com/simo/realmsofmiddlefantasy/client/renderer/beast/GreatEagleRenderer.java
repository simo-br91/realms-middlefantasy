package com.simo.realmsofmiddlefantasy.client.renderer.beast;

import com.simo.realmsofmiddlefantasy.client.model.beast.GreatEagleModel;
import com.simo.realmsofmiddlefantasy.client.renderer.BaseGeoRenderer;
import com.simo.realmsofmiddlefantasy.entity.beast.GreatEagleEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

/**
 * Renderer for Great Eagle.
 */
public class GreatEagleRenderer extends BaseGeoRenderer<GreatEagleEntity> {

    public GreatEagleRenderer(EntityRendererProvider.Context context) {
        super(context, new GreatEagleModel(), 1.5F);
    }
}
