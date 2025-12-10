package com.simo.realmsofmiddlefantasy.client.render;

import com.simo.realmsofmiddlefantasy.client.model.DwarfModel;
import com.simo.realmsofmiddlefantasy.entity.custom.DwarfEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class DwarfRenderer extends GeoEntityRenderer<DwarfEntity> {
    
    public DwarfRenderer(EntityRendererProvider.Context context) {
        super(context, new DwarfModel());
        this.shadowRadius = 0.4f;
    }
}