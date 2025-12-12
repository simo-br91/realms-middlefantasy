package com.simo.realmsofmiddlefantasy.client.render;

import com.simo.realmsofmiddlefantasy.client.model.HobbitModel;
import com.simo.realmsofmiddlefantasy.entity.custom.HobbitEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class HobbitRenderer extends GeoEntityRenderer<HobbitEntity> {
    
    public HobbitRenderer(EntityRendererProvider.Context context) {
        super(context, new HobbitModel());
        this.shadowRadius = 0.4f;
    }
}