package com.simo.realmsofmiddlefantasy.client.model;

import com.simo.realmsofmiddlefantasy.RealmsOfMiddleFantasy;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.model.GeoModel;

/**
 * Base GeoModel to reduce boilerplate (GeckoLib 4.x).
 *
 * Works with entities that implement GeoEntity.
 */
public abstract class BaseGeoModel<T extends GeoEntity> extends GeoModel<T> {

    protected static ResourceLocation rl(String path) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, path);
    }

    /** Return path like: "geo/warg.geo.json" */
    protected abstract String modelPath();

    /** Return path like: "textures/entity/warg.png" */
    protected abstract String texturePath();

    /** Return path like: "animations/warg.animation.json" */
    protected abstract String animationPath();

    @Override
    public final ResourceLocation getModelResource(T entity) {
        return rl(modelPath());
    }

    @Override
    public final ResourceLocation getTextureResource(T entity) {
        return rl(texturePath());
    }

    @Override
    public final ResourceLocation getAnimationResource(T entity) {
        return rl(animationPath());
    }
}
