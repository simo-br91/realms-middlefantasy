package com.simo.realmsofmiddlefantasy.client.render;

import com.simo.realmsofmiddlefantasy.client.model.HillTrollModel;
import com.simo.realmsofmiddlefantasy.entity.custom.HillTrollEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class HillTrollRenderer extends GeoEntityRenderer<HillTrollEntity> {
    
    public HillTrollRenderer(EntityRendererProvider.Context context) {
        super(context, new HillTrollModel());
        this.shadowRadius = 1.1f;
    }
}