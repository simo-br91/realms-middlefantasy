package com.simo.realmsofmiddlefantasy.client.render;

import com.simo.realmsofmiddlefantasy.client.model.UrukHaiModel;
import com.simo.realmsofmiddlefantasy.entity.custom.UrukHaiEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class UrukHaiRenderer extends GeoEntityRenderer<UrukHaiEntity> {
    
    public UrukHaiRenderer(EntityRendererProvider.Context context) {
        super(context, new UrukHaiModel());
        this.shadowRadius = 0.6f;
    }
}