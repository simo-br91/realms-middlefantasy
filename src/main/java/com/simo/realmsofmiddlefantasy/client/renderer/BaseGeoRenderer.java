package com.simo.realmsofmiddlefantasy.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

/**
 * Base renderer for GeckoLib GeoEntityRenderer-based entities.
 * Centralizes common renderer setup (model + shadow).
 */
public abstract class BaseGeoRenderer<T extends Entity & GeoEntity> extends GeoEntityRenderer<T> {

    protected BaseGeoRenderer(EntityRendererProvider.Context context, GeoModel<T> model, float shadowRadius) {
        super(context, model);
        this.shadowRadius = shadowRadius;
    }
}
