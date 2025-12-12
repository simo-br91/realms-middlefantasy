package com.simo.realmsofmiddlefantasy.client.renderer.beast;

import com.simo.realmsofmiddlefantasy.client.model.beast.ShadowSpiderModel;
import com.simo.realmsofmiddlefantasy.client.renderer.BaseGeoRenderer;
import com.simo.realmsofmiddlefantasy.entity.beast.ShadowSpiderEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

/**
 * Renderer for Shadow Spider.
 */
public class ShadowSpiderRenderer extends BaseGeoRenderer<ShadowSpiderEntity> {

    public ShadowSpiderRenderer(EntityRendererProvider.Context context) {
        super(context, new ShadowSpiderModel(), 0.8F);
    }
}
