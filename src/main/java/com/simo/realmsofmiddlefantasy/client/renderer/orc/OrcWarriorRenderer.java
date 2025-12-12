package com.simo.realmsofmiddlefantasy.client.renderer.orc;

import com.simo.realmsofmiddlefantasy.client.model.orc.OrcWarriorModel;
import com.simo.realmsofmiddlefantasy.entity.orc.OrcWarriorEntity;
import com.simo.realmsofmiddlefantasy.client.renderer.BaseGeoRenderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;

/**
 * Renderer for Orc Warrior.
 */
public class OrcWarriorRenderer extends BaseGeoRenderer<OrcWarriorEntity> {

    private static final float SHADOW_RADIUS = 0.5F;

    public OrcWarriorRenderer(EntityRendererProvider.Context context) {
        super(context, new OrcWarriorModel(), SHADOW_RADIUS);
    }
}
