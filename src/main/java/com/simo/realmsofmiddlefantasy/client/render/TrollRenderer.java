package com.simo.realmsofmiddlefantasy.client.render;

import com.simo.realmsofmiddlefantasy.client.model.TrollModel;
import com.simo.realmsofmiddlefantasy.entity.custom.TrollEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class TrollRenderer extends GeoEntityRenderer<TrollEntity> {
    
    public TrollRenderer(EntityRendererProvider.Context context) {
        super(context, new TrollModel());
        this.shadowRadius = 1.2f;
    }
}