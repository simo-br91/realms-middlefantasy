package com.simo.realmsofmiddlefantasy.client.renderer.elf;

import com.simo.realmsofmiddlefantasy.client.model.elf.HighElfWarriorModel;
import com.simo.realmsofmiddlefantasy.entity.elf.HighElfWarriorEntity;
import com.simo.realmsofmiddlefantasy.client.renderer.BaseGeoRenderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;

/**
 * Renderer for High Elf Warrior.
 */
public class HighElfWarriorRenderer extends BaseGeoRenderer<HighElfWarriorEntity> {

    private static final float SHADOW_RADIUS = 0.5F;

    public HighElfWarriorRenderer(EntityRendererProvider.Context context) {
        super(context, new HighElfWarriorModel(), SHADOW_RADIUS);
    }
}
