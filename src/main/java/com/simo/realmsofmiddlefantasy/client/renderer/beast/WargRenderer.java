package com.simo.realmsofmiddlefantasy.client.renderer.beast;

import com.simo.realmsofmiddlefantasy.client.model.beast.WargModel;
import com.simo.realmsofmiddlefantasy.client.renderer.BaseGeoRenderer;
import com.simo.realmsofmiddlefantasy.entity.beast.WargEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

/**
 * Renderer for Warg.
 */
public class WargRenderer extends BaseGeoRenderer<WargEntity> {

    public WargRenderer(EntityRendererProvider.Context context) {
        super(context, new WargModel(), 0.7F);
    }
}
