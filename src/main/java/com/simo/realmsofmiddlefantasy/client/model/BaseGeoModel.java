package com.simo.realmsofmiddlefantasy.client.model;

import com.simo.realmsofmiddlefantasy.RealmsOfMiddleFantasy;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.model.GeoModel;

/**
 * Base GeoModel to reduce boilerplate (GeckoLib 4.x).
 * Supports dynamic textures/animations based on entity state.
 */
public abstract class BaseGeoModel<T extends GeoEntity> extends GeoModel<T> {

    // Helper to shorten the code
    protected static ResourceLocation rl(String path) {
        return new ResourceLocation(RealmsOfMiddleFantasy.MODID, path);
    }

    /** * Return path like: "geo/warg.geo.json" 
     * @param entity The entity instance (use this if model changes based on state)
     */
    protected abstract String modelPath(T entity);

    /** * Return path like: "textures/entity/warg.png"
     * @param entity The entity instance (use this for variant textures!)
     */
    protected abstract String texturePath(T entity);

    /** * Return path like: "animations/warg.animation.json"
     * @param entity The entity instance
     */
    protected abstract String animationPath(T entity);

    @Override
    public final ResourceLocation getModelResource(T entity) {
        return rl(modelPath(entity));
    }

    @Override
    public final ResourceLocation getTextureResource(T entity) {
        return rl(texturePath(entity));
    }

    @Override
    public final ResourceLocation getAnimationResource(T entity) {
        return rl(animationPath(entity));
    }
}