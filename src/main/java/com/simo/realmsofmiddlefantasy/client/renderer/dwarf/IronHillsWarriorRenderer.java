package com.simo.realmsofmiddlefantasy.client.renderer.dwarf;

import com.simo.realmsofmiddlefantasy.client.model.dwarf.IronHillsWarriorModel;
import com.simo.realmsofmiddlefantasy.entity.dwarf.IronHillsWarriorEntity;
import com.simo.realmsofmiddlefantasy.client.renderer.BaseGeoRenderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;

/**
 * Renderer for Iron Hills Warrior.
 */
public class IronHillsWarriorRenderer extends BaseGeoRenderer<IronHillsWarriorEntity> {

    private static final float SHADOW_RADIUS = 0.5F;

    public IronHillsWarriorRenderer(EntityRendererProvider.Context context) {
        super(context, new IronHillsWarriorModel(), SHADOW_RADIUS);
    }
}
