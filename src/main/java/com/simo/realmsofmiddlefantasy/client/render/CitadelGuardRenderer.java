package com.simo.realmsofmiddlefantasy.client.render;

import com.simo.realmsofmiddlefantasy.client.model.CitadelGuardModel;
import com.simo.realmsofmiddlefantasy.entity.custom.CitadelGuardEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class CitadelGuardRenderer extends GeoEntityRenderer<CitadelGuardEntity> {
    
    public CitadelGuardRenderer(EntityRendererProvider.Context context) {
        super(context, new CitadelGuardModel());
        this.shadowRadius = 0.5f;
    }
}