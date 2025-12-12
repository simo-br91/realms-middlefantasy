package com.simo.realmsofmiddlefantasy.client.renderer.dwarf;

import com.simo.realmsofmiddlefantasy.client.model.dwarf.DwarfModel;
import com.simo.realmsofmiddlefantasy.entity.dwarf.DwarfEntity;
import com.simo.realmsofmiddlefantasy.client.renderer.BaseGeoRenderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;

/**
 * Renderer for Dwarf.
 */
public class DwarfRenderer extends BaseGeoRenderer<DwarfEntity> {

    private static final float SHADOW_RADIUS = 0.4F;

    public DwarfRenderer(EntityRendererProvider.Context context) {
        super(context, new DwarfModel(), SHADOW_RADIUS);
    }
}
