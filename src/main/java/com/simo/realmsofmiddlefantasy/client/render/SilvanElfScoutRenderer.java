package com.simo.realmsofmiddlefantasy.client.render;

import com.simo.realmsofmiddlefantasy.client.model.SilvanElfScoutModel;
import com.simo.realmsofmiddlefantasy.entity.custom.SilvanElfScoutEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SilvanElfScoutRenderer extends GeoEntityRenderer<SilvanElfScoutEntity> {
    
    public SilvanElfScoutRenderer(EntityRendererProvider.Context context) {
        super(context, new SilvanElfScoutModel());
        this.shadowRadius = 0.5f;
    }
}