package com.simo.realmsofmiddlefantasy.client.render;

import com.simo.realmsofmiddlefantasy.client.model.OrcScoutModel;
import com.simo.realmsofmiddlefantasy.entity.custom.OrcScoutEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class OrcScoutRenderer extends GeoEntityRenderer<OrcScoutEntity> {
    
    public OrcScoutRenderer(EntityRendererProvider.Context context) {
        super(context, new OrcScoutModel());
        this.shadowRadius = 0.5f;
    }
}