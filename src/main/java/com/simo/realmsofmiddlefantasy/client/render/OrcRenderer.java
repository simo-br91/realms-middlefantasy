package com.simo.realmsofmiddlefantasy.client.render;

import com.simo.realmsofmiddlefantasy.client.model.OrcModel;
import com.simo.realmsofmiddlefantasy.entity.custom.OrcEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class OrcRenderer extends GeoEntityRenderer<OrcEntity> {
    
    public OrcRenderer(EntityRendererProvider.Context context) {
        super(context, new OrcModel());
        this.shadowRadius = 0.5f;
    }
}