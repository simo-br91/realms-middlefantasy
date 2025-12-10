package com.simo.realmsofmiddlefantasy.client.render;

import com.simo.realmsofmiddlefantasy.client.model.ShadowSpiderModel;
import com.simo.realmsofmiddlefantasy.entity.custom.ShadowSpiderEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ShadowSpiderRenderer extends GeoEntityRenderer<ShadowSpiderEntity> {
    
    public ShadowSpiderRenderer(EntityRendererProvider.Context context) {
        super(context, new ShadowSpiderModel());
        this.shadowRadius = 0.8f;
    }
}