package com.simo.realmsofmiddlefantasy.client.render;

import com.simo.realmsofmiddlefantasy.client.model.OrcCaptainModel;
import com.simo.realmsofmiddlefantasy.entity.custom.OrcCaptainEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class OrcCaptainRenderer extends GeoEntityRenderer<OrcCaptainEntity> {
    
    public OrcCaptainRenderer(EntityRendererProvider.Context context) {
        super(context, new OrcCaptainModel());
        this.shadowRadius = 0.6f;
    }
}