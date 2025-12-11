package com.simo.realmsofmiddlefantasy.client.render;

import com.simo.realmsofmiddlefantasy.client.model.GaladhrimArcherModel;
import com.simo.realmsofmiddlefantasy.entity.custom.GaladhrimArcherEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class GaladhrimArcherRenderer extends GeoEntityRenderer<GaladhrimArcherEntity> {
    
    public GaladhrimArcherRenderer(EntityRendererProvider.Context context) {
        super(context, new GaladhrimArcherModel());
        this.shadowRadius = 0.5f;
    }
}