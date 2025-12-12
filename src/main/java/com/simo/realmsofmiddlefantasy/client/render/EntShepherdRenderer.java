package com.simo.realmsofmiddlefantasy.client.render;

import com.simo.realmsofmiddlefantasy.client.model.EntShepherdModel;
import com.simo.realmsofmiddlefantasy.entity.custom.EntShepherdEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class EntShepherdRenderer extends GeoEntityRenderer<EntShepherdEntity> {
    
    public EntShepherdRenderer(EntityRendererProvider.Context context) {
        super(context, new EntShepherdModel());
        this.shadowRadius = 1.5f; // Large shadow for massive Ent
    }
}