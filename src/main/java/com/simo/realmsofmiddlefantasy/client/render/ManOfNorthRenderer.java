package com.simo.realmsofmiddlefantasy.client.render;

import com.simo.realmsofmiddlefantasy.client.model.ManOfNorthModel;
import com.simo.realmsofmiddlefantasy.entity.custom.ManOfNorthEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ManOfNorthRenderer extends GeoEntityRenderer<ManOfNorthEntity> {
    
    public ManOfNorthRenderer(EntityRendererProvider.Context context) {
        super(context, new ManOfNorthModel());
        this.shadowRadius = 0.5f;
    }
}