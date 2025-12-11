package com.simo.realmsofmiddlefantasy.client.render;

import com.simo.realmsofmiddlefantasy.client.model.CaveTrollModel;
import com.simo.realmsofmiddlefantasy.entity.custom.CaveTrollEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class CaveTrollRenderer extends GeoEntityRenderer<CaveTrollEntity> {
    
    public CaveTrollRenderer(EntityRendererProvider.Context context) {
        super(context, new CaveTrollModel());
        this.shadowRadius = 1.3f;
    }
}