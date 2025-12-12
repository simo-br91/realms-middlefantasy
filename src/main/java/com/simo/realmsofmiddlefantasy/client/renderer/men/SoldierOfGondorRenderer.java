package com.simo.realmsofmiddlefantasy.client.renderer.men;

import com.simo.realmsofmiddlefantasy.client.model.men.SoldierOfGondorModel;
import com.simo.realmsofmiddlefantasy.entity.men.SoldierOfGondorEntity;
import com.simo.realmsofmiddlefantasy.client.renderer.BaseGeoRenderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;

/**
 * Renderer for Soldier of Gondor.
 */
public class SoldierOfGondorRenderer extends BaseGeoRenderer<SoldierOfGondorEntity> {

    private static final float SHADOW_RADIUS = 0.5F;

    public SoldierOfGondorRenderer(EntityRendererProvider.Context context) {
        super(context, new SoldierOfGondorModel(), SHADOW_RADIUS);
    }
}
