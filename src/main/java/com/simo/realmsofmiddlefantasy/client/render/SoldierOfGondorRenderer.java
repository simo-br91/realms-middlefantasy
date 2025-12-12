package com.simo.realmsofmiddlefantasy.client.render;

import com.simo.realmsofmiddlefantasy.client.model.SoldierOfGondorModel;
import com.simo.realmsofmiddlefantasy.entity.custom.SoldierOfGondorEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SoldierOfGondorRenderer extends GeoEntityRenderer<SoldierOfGondorEntity> {
    
    public SoldierOfGondorRenderer(EntityRendererProvider.Context context) {
        super(context, new SoldierOfGondorModel());
        this.shadowRadius = 0.5f;
    }
}