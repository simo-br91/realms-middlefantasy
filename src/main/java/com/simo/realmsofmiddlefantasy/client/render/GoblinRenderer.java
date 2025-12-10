package com.simo.realmsofmiddlefantasy.client.render;

import com.simo.realmsofmiddlefantasy.client.model.GoblinModel;
import com.simo.realmsofmiddlefantasy.entity.custom.GoblinEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class GoblinRenderer extends GeoEntityRenderer<GoblinEntity> {
    
    public GoblinRenderer(EntityRendererProvider.Context context) {
        super(context, new GoblinModel());
        this.shadowRadius = 0.3f;
    }
}