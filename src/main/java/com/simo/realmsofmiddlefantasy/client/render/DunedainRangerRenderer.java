package com.simo.realmsofmiddlefantasy.client.render;

import com.simo.realmsofmiddlefantasy.client.model.DunedainRangerModel;
import com.simo.realmsofmiddlefantasy.entity.custom.DunedainRangerEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class DunedainRangerRenderer extends GeoEntityRenderer<DunedainRangerEntity> {
    
    public DunedainRangerRenderer(EntityRendererProvider.Context context) {
        super(context, new DunedainRangerModel());
        this.shadowRadius = 0.5f;
    }
}