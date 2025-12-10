package com.simo.realmsofmiddlefantasy.client.render;

import com.simo.realmsofmiddlefantasy.client.model.WargModel;
import com.simo.realmsofmiddlefantasy.entity.custom.WargEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class WargRenderer extends GeoEntityRenderer<WargEntity> {
    
    public WargRenderer(EntityRendererProvider.Context context) {
        super(context, new WargModel());
        this.shadowRadius = 0.7f;
    }
}