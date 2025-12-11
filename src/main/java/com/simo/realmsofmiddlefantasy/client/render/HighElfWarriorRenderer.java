package com.simo.realmsofmiddlefantasy.client.render;

import com.simo.realmsofmiddlefantasy.client.model.HighElfWarriorModel;
import com.simo.realmsofmiddlefantasy.entity.custom.HighElfWarriorEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class HighElfWarriorRenderer extends GeoEntityRenderer<HighElfWarriorEntity> {
    
    public HighElfWarriorRenderer(EntityRendererProvider.Context context) {
        super(context, new HighElfWarriorModel());
        this.shadowRadius = 0.5f;
    }
}