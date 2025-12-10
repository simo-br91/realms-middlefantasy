package com.simo.realmsofmiddlefantasy.client.render;

import com.simo.realmsofmiddlefantasy.client.model.WightModel;
import com.simo.realmsofmiddlefantasy.entity.custom.WightEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class WightRenderer extends GeoEntityRenderer<WightEntity> {
    
    public WightRenderer(EntityRendererProvider.Context context) {
        super(context, new WightModel());
        this.shadowRadius = 0.5f;
    }
}